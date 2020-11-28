package com.example.adewijanugraha.healthycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtKet;
    EditText edtNama, edtUmur, edtTinggi, edtBerat, edtEmail;
    Button btnReset, btnSubmit, btnKalori, btnBmi;
    RadioButton cekLaki, cekPerempuan;
    RadioGroup radio;
    String nama, tinggis, berats, umurs, jk, email;
    boolean kelamin, cek;
    int tinggi, berat, umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNama = (EditText) findViewById(R.id.edtNama);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUmur = (EditText) findViewById(R.id.edtUmur);
        edtTinggi = (EditText) findViewById(R.id.edtTinggi);
        edtBerat = (EditText) findViewById(R.id.edtBerat);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnKalori = (Button) findViewById(R.id.btnKalori);
        btnBmi = (Button) findViewById(R.id.btnBmi);
        cekLaki = (RadioButton) findViewById(R.id.cekLaki);
        cekPerempuan = (RadioButton) findViewById(R.id.cekPerempuan);
        txtKet = (TextView) findViewById(R.id.txtKet);
        radio = (RadioGroup) findViewById(R.id.radio);
        if (kelamin) {
            jk = "Laki-laki";
        } else jk = "Perempuan";
        txtKet.setVisibility(View.INVISIBLE);
    }

    public void home(View view) {
        Toast.makeText(this, "HOME", Toast.LENGTH_LONG).show();
    }

    public void bmi(View view) {
        Intent intent = new Intent(this, BMIActivity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("email", email);
        intent.putExtra("berat", berat);
        intent.putExtra("umur", umur);
        intent.putExtra("tinggi", tinggi);
        intent.putExtra("kelamin", kelamin);
        startActivity(intent);
    }

    public void kalori(View view) {
        Intent intent = new Intent(this, KaloriActivity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("email", email);
        intent.putExtra("berat", berat);
        intent.putExtra("umur", umur);
        intent.putExtra("tinggi", tinggi);
        intent.putExtra("kelamin", kelamin);
        startActivity(intent);
    }

    public void submit(View view) {
        isChecked();
        if (cek) {
            nama = edtNama.getText().toString();
            email = edtEmail.getText().toString();
            umurs = edtUmur.getText().toString();
            umur = Integer.parseInt(umurs);
            berats = edtBerat.getText().toString();
            berat = Integer.parseInt(berats);
            tinggis = edtTinggi.getText().toString();
            tinggi = Integer.parseInt(tinggis);
            if (cekLaki.isChecked()) {
                kelamin = true;
            }
            if (cekPerempuan.isChecked()) {
                kelamin = false;
            }
            if (nama != null && email != null && umurs != null && berats != null && tinggis != null && cekLaki.isChecked() || cekPerempuan.isChecked()) {
                Toast.makeText(this, "Data Berhasil Di Input", Toast.LENGTH_LONG).show();
            }
        } else {
            txtKet.setVisibility(View.VISIBLE);
            txtKet.setText("Proses Input Gagal. Inputan Tidak Lengkap");
            Toast.makeText(this, "Cek Inputan", Toast.LENGTH_LONG).show();
        }
    }

    public void reset(View view) {
        edtNama.setText("");
        edtEmail.setText("");
        edtTinggi.setText("");
        edtBerat.setText("");
        edtUmur.setText("");
        cekPerempuan.setChecked(false);
        cekLaki.setChecked(false);
        nama = null;
        email = null;
        umur = 0;
        berat = 0;
        tinggi = 0;
        if (nama == null && umur == 0 && email == null && tinggi == 0 && berat == 0 && !cekLaki.isChecked() && !cekPerempuan.isChecked()) {
            Toast.makeText(this, "Data Berhasil Di Reset", Toast.LENGTH_LONG).show();
        }
    }

    public void isChecked() {
        cek = true;
        if (edtNama.getText().length() == 0) {
            cek = false;
            edtNama.setError("Isi Kolom Nama");
        } else if (edtEmail.getText().length() == 0) {
            cek = false;
            edtEmail.setError("Isi Kolom E-Mail");
        } else if (edtUmur.getText().length() == 0) {
            cek = false;
            edtUmur.setError("Isi Kolom Umur");
        } else if (edtBerat.getText().length() == 0) {
            cek = false;
            edtBerat.setError("Isi Kolom Berat Badan");
        } else if (edtTinggi.getText().length() == 0) {
            cek = false;
            edtTinggi.setError("Isi Kolom Tinggi Badan");
        } else if (!cekPerempuan.isChecked() && !cekLaki.isChecked()) {
            cek = false;
        }
    }
}
