package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private EditText cityEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        cityEditText = findViewById(R.id.cityEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String city = cityEditText.getText().toString();
            SharedPreferences sharedPreferences = getSharedPreferences("WeatherPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("city", city);
            editor.apply();

            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        });


    }
}
