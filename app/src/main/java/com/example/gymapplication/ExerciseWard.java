package com.example.gymapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymapplication.dto.TrainerResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class ExerciseWard extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button saveit;
    //EditText name, surname, personalIdNumber;

    String URL;
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    List<String> trainers = new LinkedList<>();
    List<TrainerResponse> trainersResponse = new LinkedList<>();
    String trainer = null;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_ward);
        URL = getString(R.string.API_URL);
        //name = findViewById(R.id.zajecia_imie);
        //surname = findViewById(R.id.zajecia_nazwisko);
        //personalIdNumber = findViewById(R.id.zajecia_pesel);
        saveit = findViewById(R.id.saveZajecia);
        saveit.setOnClickListener(this);


        //Odczyt z pamieci wspoldzielonej
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        token = preferences.getString("token", "defaultValue");
        System.out.println(token);

        Request request = new Request.Builder()
                .url(URL + "/api/trainers")
                .addHeader("Authorization", token)
                .build();

        System.out.println(request.headers());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println(e.getMessage());
            }
            @RequiresApi(api= Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                TrainerResponse[] trainerArray = gson.fromJson(response.body().string(), TrainerResponse[].class);
                for (TrainerResponse p : trainerArray){
                    trainersResponse.add(p);
                    trainers.add(p.getName() + " " + p.getSurname());
                }
            }
        });
        AutoCompleteTextView allTrainer = findViewById(R.id.autocompleteTrainer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, trainers);
        allTrainer.setAdapter(adapter);
        allTrainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                trainer = parent.getItemAtPosition(position).toString();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.saveZajecia){
            System.out.println(trainer);
            List<TrainerResponse> trainerCode = trainersResponse.stream().filter(item -> Objects.equals(item.getName() + " " + item.getSurname(), trainer)).collect(Collectors.toList());

            Request request = new Request.Builder()
                    .url(URL + "/api/trainers/" + trainerCode.get(0).getCode()+"/addWard")
                    .addHeader("Authorization", token)
                    .post(new RequestBody() {
                        @Nullable
                        @Override
                        public MediaType contentType() {
                            return null;
                        }

                        @Override
                        public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {

                        }
                    })
                    .build();
            add(request);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        System.out.println(parent.getItemAtPosition(0));
        System.out.println(i);
        trainer = parent.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void add(Request request){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                System.out.println(response);
            }
        });
    }
}