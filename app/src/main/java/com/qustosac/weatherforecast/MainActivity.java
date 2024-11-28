package com.qustosac.weatherforecast;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.qustosac.weatherforecast.api.RetrofitClient;
import com.qustosac.weatherforecast.api.WeatherApi;
import com.qustosac.weatherforecast.model.ForecastResponse;
import com.qustosac.weatherforecast.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView currentTemperature;
    private TextView currentLocation;
    private ImageView weatherIcon;
    private RecyclerView hourlyForecast;
    private FusedLocationProviderClient fusedLocationClient;
    private HourlyForecastAdapter forecastAdapter;
    private static final String API_KEY = "d081e8322725009d5a5d29d0be06b0e8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTemperature = findViewById(R.id.currentTemperature);
        currentLocation = findViewById(R.id.currentLocation);
        weatherIcon = findViewById(R.id.weatherIcon);
        hourlyForecast = findViewById(R.id.hourlyForecast);

        hourlyForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        forecastAdapter = new HourlyForecastAdapter();
        hourlyForecast.setAdapter(forecastAdapter);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    fetchWeatherData(location.getLatitude(), location.getLongitude());
                } else {
                    Toast.makeText(MainActivity.this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchWeatherData(double lat, double lon) {
        WeatherApi weatherApi = RetrofitClient.getClient("https://api.openweathermap.org/data/2.5/").create(WeatherApi.class);

        weatherApi.getCurrentWeather(lat, lon, API_KEY)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful()) {
                            WeatherResponse weatherResponse = response.body();

                            double tempInCelsius = weatherResponse.getMain().getTemp() - 273.15;
                            currentTemperature.setText(String.format("%.1f°C", tempInCelsius));
                            currentLocation.setText(weatherResponse.getName());

                            String iconCode = weatherResponse.getWeather().get(0).getIcon();
                            String iconUrl = "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
                            Glide.with(MainActivity.this).load(iconUrl).into(weatherIcon);
                        } else {
                            Toast.makeText(MainActivity.this, "Ошибка получения данных текущей погоды", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Получение прогноза
        weatherApi.getForecast(lat, lon, API_KEY)
                .enqueue(new Callback<ForecastResponse>() {
                    @Override
                    public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                        if (response.isSuccessful()) {
                            ForecastResponse forecastResponse = response.body();
                            forecastAdapter.setForecastList(forecastResponse.getList());
                        } else {
                            Toast.makeText(MainActivity.this, "Ошибка получения данных прогноза", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            } else {
                Toast.makeText(this, "Доступ к местоположению необходим для работы приложения", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
