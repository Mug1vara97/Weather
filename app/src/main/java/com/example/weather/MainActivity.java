package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView temperatureTextView;
    private TextView weatherDescriptionTextView;

    private static final String API_KEY = "16f7f65291e2b3f4e2353752546166b7";

    private TextView weatherForecastTextView;

    private TextView day1ForecastTextView;
    private TextView day2ForecastTextView;
    private TextView day3ForecastTextView;
    private TextView day4ForecastTextView;
    private TextView day5ForecastTextView;

    private ImageView day1IconImageView;
    private ImageView day2IconImageView;
    private ImageView day3IconImageView;
    private ImageView day4IconImageView;
    private ImageView day5IconImageView;

    private TextView day1DateTextView;
    private TextView day2DateTextView;
    private TextView day3DateTextView;
    private TextView day4DateTextView;
    private TextView day5DateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTextView = findViewById(R.id.temperatureTextView);
        weatherDescriptionTextView = findViewById(R.id.weatherDescriptionTextView);
        day1ForecastTextView = findViewById(R.id.day1ForecastTextView);
        day2ForecastTextView = findViewById(R.id.day2ForecastTextView);
        day3ForecastTextView = findViewById(R.id.day3ForecastTextView);
        day4ForecastTextView = findViewById(R.id.day4ForecastTextView);
        day5ForecastTextView = findViewById(R.id.day5ForecastTextView);

        day1IconImageView = findViewById(R.id.day1IconImageView);
        day2IconImageView = findViewById(R.id.day2IconImageView);
        day3IconImageView = findViewById(R.id.day3IconImageView);
        day4IconImageView = findViewById(R.id.day4IconImageView);
        day5IconImageView = findViewById(R.id.day5IconImageView);

        day1DateTextView = findViewById(R.id.day1DateTextView);
        day2DateTextView = findViewById(R.id.day2DateTextView);
        day3DateTextView = findViewById(R.id.day3DateTextView);
        day4DateTextView = findViewById(R.id.day4DateTextView);
        day5DateTextView = findViewById(R.id.day5DateTextView);

        SharedPreferences sharedPreferences = getSharedPreferences("WeatherPrefs", MODE_PRIVATE);
        String city = sharedPreferences.getString("city", "");
        if (!city.isEmpty()) {
            getWeather(city);
            getWeatherForecast(city);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void getWeatherForecast(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<ForecastResponse> call = weatherApi.getWeatherForecast(city, API_KEY);

        call.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    List<Forecast> forecastList = response.body().getForecastList();

                    int dayCounter = 1;
                    for (Forecast forecast : forecastList) {
                        long timestamp = forecast.getTimestamp();
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp * 1000L));
                        String weatherDescription = forecast.getWeatherDescription();
                        double temperatureKelvin = forecast.getTemperature();
                        double temperatureCelsius = temperatureKelvin - 273.15;
                        double roundedTemperature = Math.round(temperatureCelsius * 10.0) / 10.0;

                        String dateText = date;
                        String forecastText = roundedTemperature + "°\n";

                        switch (dayCounter) {
                            case 1:
                                day1DateTextView.append(dateText);
                                day1ForecastTextView.append(forecastText);
                                day1IconImageView.setImageResource(getWeatherIconResource(forecast.getWeatherDescription()));
                                break;
                            case 2:
                                day2DateTextView.append(dateText);
                                day2ForecastTextView.append(forecastText);
                                day2IconImageView.setImageResource(getWeatherIconResource(forecast.getWeatherDescription()));
                                break;
                            case 3:
                                day3DateTextView.append(dateText);
                                day3ForecastTextView.append(forecastText);
                                day3IconImageView.setImageResource(getWeatherIconResource(forecast.getWeatherDescription()));
                                break;
                            case 4:
                                day4DateTextView.append(dateText);
                                day4ForecastTextView.append(forecastText);
                                day4IconImageView.setImageResource(getWeatherIconResource(forecast.getWeatherDescription()));
                                break;
                            case 5:
                                day5DateTextView.append(dateText);
                                day5ForecastTextView.append(forecastText);
                                day5IconImageView.setImageResource(getWeatherIconResource(forecast.getWeatherDescription()));
                                break;
                        }
                        dayCounter++;
                    }

                } else {
                    String errorMessage = "Failed to get weather forecast data";
                    day1ForecastTextView.setText(errorMessage);
                    day2ForecastTextView.setText(errorMessage);
                    day3ForecastTextView.setText(errorMessage);
                    day4ForecastTextView.setText(errorMessage);
                    day5ForecastTextView.setText(errorMessage);
                }
            }


            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                String errorMessage = "Failed to get weather forecast data";
                day1ForecastTextView.setText(errorMessage);
                day2ForecastTextView.setText(errorMessage);
                day3ForecastTextView.setText(errorMessage);
                day4ForecastTextView.setText(errorMessage);
                day5ForecastTextView.setText(errorMessage);
            }
        });
    }



    private void getWeather(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeather(city, API_KEY);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherInfo weatherInfo = response.body().getWeatherInfo();
                    List<Weather> weatherList = response.body().getWeather();

                    double temperatureKelvin = weatherInfo.getTemperature();
                    double temperatureCelsius = temperatureKelvin - 273.15;
                    double roundedTemperature = Math.round(temperatureCelsius * 10.0) / 10.0;

                    String weatherDescription = "";
                    for (Weather weather : weatherList) {
                        weatherDescription += weather.getDescription() + " ";
                    }

                    temperatureTextView.setText(roundedTemperature + "°");
                    weatherDescriptionTextView.setText(weatherDescription);
                } else {
                    temperatureTextView.setText("Failed to get weather data");
                    weatherDescriptionTextView.setText("");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                temperatureTextView.setText("Failed to get weather data");
                weatherDescriptionTextView.setText("");
            }
        });
    }
    private int getWeatherIconResource(String weatherDescription) {
        int iconResource = R.drawable.clear_sky;

        if (weatherDescription.contains("overcast clouds")) {
            iconResource = R.drawable.broken_clouds;
        } else if (weatherDescription.contains("sun")) {
            iconResource = R.drawable.snow;
        } else if (weatherDescription.contains("cloud")) {
            iconResource = R.drawable.mist;
        }

        return iconResource;
    }
}
