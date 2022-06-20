package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.login_log);
        inputPassword = (EditText) findViewById(R.id.log_pass);
        btnLogin = (Button) findViewById(R.id.zaloguj_log);

    }
}