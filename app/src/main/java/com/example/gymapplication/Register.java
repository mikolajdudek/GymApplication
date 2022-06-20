package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail = (EditText) findViewById(R.id.login_rej);
        inputPassword = (EditText) findViewById(R.id.rej_pass);
        btnregister = (Button) findViewById(R.id.zaloguj_rej);
    }
}