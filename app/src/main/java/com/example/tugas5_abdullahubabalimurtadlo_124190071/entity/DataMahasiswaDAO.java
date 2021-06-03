package com.example.tugas5_abdullahubabalimurtadlo_124190071.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataMahasiswaDAO {
    // insert data
    @Insert
    Long insertData(DataMahasiswa dataMahasiswa);

    // show data urut berdasarkan nim kecil ke besar
    @Query("Select * from mahasiswa_db order by nim")
    List<DataMahasiswa> getData();

    // update data (bisa update nim)
    @Query("Update mahasiswa_db set nim = :nim, nama = :nama, prodi = :prodi, jurusan = :jurusan, ipk = :ipk where nim = :pKey ")
    int updateData(String nim, String nama, String prodi, String jurusan, Float ipk, String pKey);

    // delete data
    @Delete
    void deleteData(DataMahasiswa item);
}
