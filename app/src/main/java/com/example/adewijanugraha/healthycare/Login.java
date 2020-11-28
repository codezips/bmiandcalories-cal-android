package com.example.adewijanugraha.healthycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText edtUser, edtPass;
    Button btnLogin;
    String pass, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.username);
        edtPass = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    public void login(View view){
        pass = edtPass.getText().toString();
        user = edtUser.getText().toString();
        if (user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("1234")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            Intent intent = new Intent(this, LoginFail.class);
            startActivity(intent);
            finish();
        }
    }
}
