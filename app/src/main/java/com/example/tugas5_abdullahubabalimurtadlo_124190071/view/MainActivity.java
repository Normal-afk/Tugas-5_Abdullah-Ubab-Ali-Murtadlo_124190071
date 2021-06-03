package com.example.tugas5_abdullahubabalimurtadlo_124190071.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tugas5_abdullahubabalimurtadlo_124190071.R;
import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.AppDatabase;
import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.DataMahasiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.mainView, View.OnClickListener {
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private FloatingActionButton btn_add;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.rcv_main);

        // koneksi ke database
        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);

        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, InsertAcivity.class);
        intent.putExtra("edit", false);
        startActivity(intent);
        finish();
    }

    @Override
    public void successDelete() {
        Toast.makeText(getApplicationContext(),"Berhasil Menghapus Data",Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void getData(List<DataMahasiswa> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataMahasiswa item) {
        Intent intent = new Intent(MainActivity.this, InsertAcivity.class);
        intent.putExtra("nim",item.getNim());
        intent.putExtra("nama",item.getNama());
        intent.putExtra("prodi",item.getProdi());
        intent.putExtra("jurusan",item.getJurusan());
        intent.putExtra("ipk",item.getIpk());
        intent.putExtra("edit", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void deleteData(DataMahasiswa item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin akan menghapus data ini?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainPresenter.deleteData(item,appDatabase); // proses hapus data
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

    }
}