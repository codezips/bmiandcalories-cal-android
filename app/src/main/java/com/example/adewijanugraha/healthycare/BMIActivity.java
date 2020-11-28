package com.example.adewijanugraha.healthycare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BMIActivity extends AppCompatActivity {
    String nama, hasil, saran, ket, jk, email;
    boolean kelamin;
    int tinggi, berat, umur;
    double hitung, ideal;
    TextView txtHasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        Bundle bundle = getIntent().getExtras();
        nama = bundle.getString("nama");
        email = bundle.getString("email");
        tinggi = bundle.getInt("tinggi");
        berat = bundle.getInt("berat");
        kelamin = bundle.getBoolean("kelamin");
        umur = bundle.getInt("umur");
        txtHasil = (TextView) findViewById(R.id.txtHasil);

        if (kelamin) {
            jk = "Laki-laki";
        } else jk = "Perempuan";
        hitungIMT();
        ideal();
        ket();
    }

    public void home(View view) {
        finish();
    }

    public void bmi(View view) {
        Toast.makeText(this, "IMT", Toast.LENGTH_LONG).show();
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
        finish();
    }

    public void hitungIMT() {
        double ban = ((double) tinggi / 100) * ((double) tinggi / 100);
        hitung = (double) berat / ban;

        if (hitung < 17 && kelamin) {
            hasil = "Under Weight / Kurus";
            saran = "menambah konsumsi makanan berkalori";
        } else if (hitung < 24 && kelamin) {
            hasil = "Normal Weight / Normal";
            saran = "cukup berolaraga dengan rutin dan menjaga kesehatan";
        } else if (hitung < 28 && kelamin) {
            hasil = "Over Weight / Kegemukan";
            saran = "berolaraga dengan rutin dan membatasi asupan kalori";
        } else if (hitung > 27 && kelamin) {
            hasil = "Obesitas";
            saran = "menjalani program penurunan berat badan dan rajin berolahraga";
        }

        if (hitung < 18 && !kelamin) {
            hasil = "Under Weight / Kurus";
            saran = "menambah konsumsi makanan berkalori";
        } else if (hitung < 26 && !kelamin) {
            hasil = "Normal Weight / Normal";
            saran = "cukup berolaraga dengan rutin dan menjaga kesehatan";
        } else if (hitung < 28 && !kelamin) {
            hasil = "Over Weight / Kegemukan";
            saran = "berolaraga dengan rutin dan membatasi asupan kalori";
        } else if (hitung > 27 && !kelamin) {
            hasil = "Obesitas";
            saran = "menjalani program penurunan berat badan dan rajin berolahraga";
        }
    }

    public void ideal() {
        if (kelamin) {
            ideal = (tinggi - 100) - ((double) 10 / 100 * (tinggi - 100));
        } else {
            ideal = (tinggi - 100) - ((double) 15 / 100 * (tinggi - 100));
        }
    }

    public void ket() {
        ket = "Saudara " + nama + ", dari data yang sudah diinputkan yaitu :\nJenis Kelamin : " + jk + "\nBerat Badan : " + berat + " kg\nTinggi Badan : " + tinggi + " cm" +
                "\nMaka Indeks Massa Tubuh (IMT) Anda  tergolong " + hasil + " dan disarankan untuk " + saran + "." +
                "\nBerat badan ideal yang disarankan untuk Anda adalah " + ideal + " kg";
        txtHasil.setText(ket);
    }

    public void button(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hasil Indeks Massa Tubuh Saudara " + nama);
        intent.putExtra(Intent.EXTRA_TEXT, ket);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
