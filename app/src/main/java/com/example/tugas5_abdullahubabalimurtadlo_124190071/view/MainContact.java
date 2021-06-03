package com.example.tugas5_abdullahubabalimurtadlo_124190071.view;

import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.AppDatabase;
import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.DataMahasiswa;

import java.util.List;

public interface MainContact {
    // untuk kodingan MainActivity
    interface mainView {
        void successDelete();
        void getData(List<DataMahasiswa> list);
        void editData(DataMahasiswa item);
        void deleteData(DataMahasiswa item);
    }
    // untuk kodingan InsertActivity
    interface insertView {
        void successAdd();
        void resetForm();
    }
    // untuk database
    interface presenter {
        void insertData(String nim, String nama, String prodi, String jurusan, Float ipk, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nim, String nama, String prodi, String jurusan, Float ipk,String pkey, AppDatabase database);
        void deleteData(DataMahasiswa dataMahasiswa, AppDatabase database);
    }
}
