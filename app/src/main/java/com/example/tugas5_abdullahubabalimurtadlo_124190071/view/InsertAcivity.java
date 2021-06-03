package com.example.tugas5_abdullahubabalimurtadlo_124190071.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas5_abdullahubabalimurtadlo_124190071.R;
import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.AppDatabase;

public class InsertAcivity extends AppCompatActivity implements MainContact.insertView, View.OnClickListener {
    private MainPresenter mainPresenter;
    private AppDatabase appDatabase;

    private EditText etNIM, etNama, etProdi, etJurusan, etIPK;
    private Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        etNIM = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        etProdi = findViewById(R.id.et_prodi);
        etJurusan = findViewById(R.id.et_jurusan);
        etIPK = findViewById(R.id.et_ipk);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSubmit = findViewById(R.id.btn_submit);

        mainPresenter = new MainPresenter(this);
        appDatabase = AppDatabase.inidb(getApplicationContext());

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        if (getIntent().getExtras().getBoolean("edit") == true){
            editData();
        }
    }

    private void editData() {
        etNIM.setText(getIntent().getExtras().getString("nim"));
        etNama.setText(getIntent().getExtras().getString("nama"));
        etProdi.setText(getIntent().getExtras().getString("prodi"));
        etJurusan.setText(getIntent().getExtras().getString("jurusan"));
        etIPK.setText(Float.toString(getIntent().getExtras().getFloat("ipk")));
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel){
            resetForm(); // reset form
            loadList(); // kembali ke tampilan data
        }
        if (v == btnSubmit){
            // jika form ada yang kosong
            if (etNIM.getText().toString().equals("")||etNama.getText().toString().equals("")||etProdi.getText().toString().equals("")||etJurusan.getText().toString().equals("")||etIPK.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Mohon Isi Semua Data!",Toast.LENGTH_SHORT).show();
            }
            // jika form terisi semua
            else {
                // jika edit
                if (getIntent().getExtras().getBoolean("edit") == true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Mengedit Data")
                            .setMessage("Anda yakin akan menyimpan data ini?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mainPresenter.editData(etNIM.getText().toString(),etNama.getText().toString(),etProdi.getText().toString(),etJurusan.getText().toString(),Float.parseFloat(etIPK.getText().toString()),getIntent().getExtras().getString("nim"),appDatabase);
                                    resetForm(); // reset form
                                    loadList(); // kembali ke tampilan data
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
                // jika insert
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Insert Data")
                            .setMessage("Anda yakin akan menyimpan data ini?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mainPresenter.insertData(etNIM.getText().toString(),etNama.getText().toString(),etProdi.getText().toString(),etJurusan.getText().toString(), Float.parseFloat(etIPK.getText().toString()),appDatabase);
                                    resetForm(); // reset form
                                    loadList(); // kembali ke tampilan data
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            }
        }
    }

    private void loadList() {
        Intent intent = new Intent(InsertAcivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void successAdd() {
        Toast.makeText(getApplicationContext(),"Berhasil Memasukkan Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetForm() {
        etNIM.setText("");
        etNama.setText("");
        etProdi.setText("");
        etJurusan.setText("");
        etIPK.setText("");
    }
}