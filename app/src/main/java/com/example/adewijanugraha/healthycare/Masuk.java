package com.example.adewijanugraha.healthycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Masuk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
    }

    public void masuk(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
