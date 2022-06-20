package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ExerciseWard extends AppCompatActivity {
    Button saveit;
    EditText name, surname, personalIdNumber;
    Spinner allTrainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_ward);

        name = findViewById(R.id.zajecia_imie);
        surname = findViewById(R.id.zajecia_nazwisko);
        personalIdNumber = findViewById(R.id.zajecia_pesel);
        saveit = findViewById(R.id.zajecia_button);
        allTrainer = findViewById(R.id.spinner);


    }
}