package com.example.tugas5_abdullahubabalimurtadlo_124190071.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "mahasiswa_db") // nama tabel
public class DataMahasiswa {
    // primary key -> nim
    @NonNull
    @PrimaryKey (autoGenerate = false)
    @ColumnInfo (name = "nim")
    private String nim;

    // nama
    @ColumnInfo (name = "nama")
    private String nama;

    // prodi
    @ColumnInfo (name = "prodi")
    private String prodi;

    // jurusan
    @ColumnInfo (name = "jurusan")
    private String jurusan;

    //ipk
    @ColumnInfo (name = "ipk")
    private Float ipk;

    // setter dan getter semua kolom pada tabel mahasiswa_db

    @NonNull
    public String getNim() {
        return nim;
    }

    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public Float getIpk() {
        return ipk;
    }

    public void setIpk(Float ipk) {
        this.ipk = ipk;
    }
}
