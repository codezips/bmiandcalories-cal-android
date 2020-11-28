package com.example.adewijanugraha.healthycare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class KaloriActivity extends AppCompatActivity {
    String nama, jk, ketKal, email;
    boolean kelamin;
    int tinggi, berat, umur;
    double act, hasil;
    TextView txtHasil;
    RadioButton btnTidak, btnRingan, btnSedang, btnBerat, btnSaBer;
    Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);

        Bundle bundle = getIntent().getExtras();
        nama = bundle.getString("nama");
        tinggi = bundle.getInt("tinggi");
        berat = bundle.getInt("berat");
        umur = bundle.getInt("umur");
        kelamin = bundle.getBoolean("kelamin");
        email = bundle.getString("email");

        txtHasil = (TextView) findViewById(R.id.txtHasil);
        btnTidak = (RadioButton) findViewById(R.id.btnTidak);
        btnRingan = (RadioButton) findViewById(R.id.btnRingan);
        btnSedang = (RadioButton) findViewById(R.id.btnSedang);
        btnBerat = (RadioButton) findViewById(R.id.btnBerat);
        btnSaBer = (RadioButton) findViewById(R.id.btnSaBer);
        btnHitung = (Button) findViewById(R.id.btnHitung);
    }

    public void home(View view) {
        finish();
    }

    public void bmi(View view) {

        Intent intent = new Intent(this, BMIActivity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("email", email);
        intent.putExtra("berat", berat);
        intent.putExtra("umur", umur);
        intent.putExtra("tinggi", tinggi);
        intent.putExtra("kelamin", kelamin);
        intent.putExtra("ketKal", ketKal);
        startActivity(intent);
        finish();
    }

    public void kalori(View view) {
        Toast.makeText(this, "KALORI", Toast.LENGTH_LONG).show();
    }

    public void aktivitas() {
        if (btnTidak.isChecked()) {
            act = 1.2;
        } else if (btnRingan.isChecked()) {
            act = 1.375;
        } else if (btnSedang.isChecked()) {
            act = 1.55;
        } else if (btnBerat.isChecked()) {
            act = 1.725;
        } else if (btnSaBer.isChecked()) {
            act = 1.9;
        }
    }

    public void hitung(View view) {
        aktivitas();
        if (kelamin) {
            jk = "Laki-laki";
        } else jk = "Perempuan";
        int count = 0;
        if (kelamin) {
            double temp = 66.5 + (13.8 * berat) + (5 * tinggi) / (6.8 * umur);
            hasil = temp * act;
        } else {
            double temp = 655.1 + (9.6 * berat) + (1.9 * tinggi) / (4.7 * umur);
            hasil = temp * act;
        }
        count++;
        if (count != 0) {
            btnHitung.setEnabled(false);
        }
        txtHasil.setText(keterangan());
    }

    public String keterangan() {
        ketKal = "Saudara " + nama + ", dari data yang sudah diinputkan yaitu :\nJenis Kelamin : " + jk + "\nBerat Badan : " + berat + " kg\nTinggi Badan : " + tinggi + " cm" +
                "\nMaka total kalori yang Anda butuhkan sehari sebesar " + (int) hasil + " kkal atau setara dengan " + (int) hasil / 400 + " piring nasi";
        return ketKal;
    }

    public void button(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hasil Perhitungan Kalori Harian Saudara " + nama);
        intent.putExtra(Intent.EXTRA_TEXT, ketKal);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
