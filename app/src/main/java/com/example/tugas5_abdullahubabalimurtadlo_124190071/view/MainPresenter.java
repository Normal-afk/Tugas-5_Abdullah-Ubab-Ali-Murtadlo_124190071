package com.example.tugas5_abdullahubabalimurtadlo_124190071.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.AppDatabase;
import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.DataMahasiswa;

import java.util.List;

public class MainPresenter implements MainContact.presenter{ // implement interface presenter dari maincontact

    private MainContact.mainView mainView;
    private MainContact.insertView insertView;

    public MainPresenter(MainContact.insertView insertView) {
        this.insertView = insertView;
    }

    public MainPresenter(MainContact.mainView mainView) {
        this.mainView = mainView;
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private AppDatabase appDatabase;
        private DataMahasiswa dataMahasiswa;

        public InsertData(AppDatabase appDatabase, DataMahasiswa dataMahasiswa) {
            this.appDatabase = appDatabase;
            this.dataMahasiswa = dataMahasiswa;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataMahasiswa); // mengkases insert data pada DataMahasiswaDAO dengan parameter dataMahasiswa
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            insertView.successAdd();
        }
    }

    @Override
    public void insertData(String nim, String nama, String prodi, String jurusan, Float ipk, AppDatabase database) {
        final DataMahasiswa dataMahasiswa = new DataMahasiswa();
        dataMahasiswa.setNim(nim);
        dataMahasiswa.setNama(nama);
        dataMahasiswa.setProdi(prodi);
        dataMahasiswa.setJurusan(jurusan);
        dataMahasiswa.setIpk(ipk);
        new InsertData(database, dataMahasiswa).execute(); // input data ke db
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataMahasiswa> list;
        list = database.dao().getData(); // mengambil data dari database ke dalam list
        mainView.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer>{
        private AppDatabase appDatabase;
        private DataMahasiswa dataMahasiswa;
        private String pKey;

        public EditData(AppDatabase appDatabase, DataMahasiswa dataMahasiswa, String pKey) {
            this.appDatabase = appDatabase;
            this.dataMahasiswa = dataMahasiswa;
            this.pKey = pKey;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            // update data where nim = pKey
            return appDatabase.dao().updateData(dataMahasiswa.getNim(),dataMahasiswa.getNama(),dataMahasiswa.getProdi(),dataMahasiswa.getJurusan(),dataMahasiswa.getIpk(),pKey); // data baru masuk ke query update data
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            insertView.successAdd();
        }
    }

    @Override
    public void editData(String nim, String nama, String prodi, String jurusan, Float ipk, String pKey, AppDatabase database) {
        final DataMahasiswa dataMahasiswa = new DataMahasiswa();
        dataMahasiswa.setNim(nim);
        dataMahasiswa.setNama(nama);
        dataMahasiswa.setProdi(prodi);
        dataMahasiswa.setJurusan(jurusan);
        dataMahasiswa.setIpk(ipk);
        new EditData(database, dataMahasiswa, pKey).execute(); // update data + kirim pKey
    }

    class DeleteData extends AsyncTask<Void, Void, Long>{

        private AppDatabase appDatabase;
        private DataMahasiswa dataMahasiswa;

        public DeleteData(AppDatabase appDatabase, DataMahasiswa dataMahasiswa) {
            this.appDatabase = appDatabase;
            this.dataMahasiswa = dataMahasiswa;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataMahasiswa); // masuk query delete
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            mainView.successDelete();   // berhasil menghapus
        }
    }

    @Override
    public void deleteData(DataMahasiswa dataMahasiswa, AppDatabase database) {
        new DeleteData(database,dataMahasiswa).execute();   // delete data
    }
}
