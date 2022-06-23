package com.example.gymapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gymapplication.dto.LoginCredentials;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity implements View.OnClickListener{
    EditText inputlogin, inputPassword;
    Button btnLogin;
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        URL = getString(R.string.API_URL);
        inputlogin = (EditText) findViewById(R.id.login_log);
        inputPassword = (EditText) findViewById(R.id.log_pass);
        btnLogin = (Button) findViewById(R.id.zaloguj_log);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
        if(view.getId() == R.id.zaloguj_log){
            inputlogin = findViewById(R.id.login_log);
            inputPassword = findViewById(R.id.log_pass);

            LoginCredentials loginCredentials = new LoginCredentials(inputlogin.getText().toString(), inputPassword.getText().toString());
            String loginJson = gson.toJson(loginCredentials);

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), loginJson);

            Request request = new Request.Builder()
                    .url(URL+"/login")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    System.out.println("Bład");
                    System.out.println(e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    //System.out.println(response.headers());
                    //System.out.println(response.headers().get("Authorization"));

                    //Zapis do pamięci wspoldzielonej
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    //Save values from Edit Text Controls
                    preferences.edit().putString("token", response.headers().get("Authorization")).apply();

                    openMenu();
                }
            });
        }
    }

    public void openMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}