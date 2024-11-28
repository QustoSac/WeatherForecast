package com.qustosac.weatherforecast.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("main")
    private Main main;

    @SerializedName("name")
    private String name;

    @SerializedName("weather") // Добавлено
    private List<Weather> weather;

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public List<Weather> getWeather() { // Добавлено
        return weather;
    }

    public static class Main {
        @SerializedName("temp")
        private float temp;

        public float getTemp() {
            return temp;
        }
    }

    public static class Weather { // Класс для описания "weather"
        @SerializedName("icon")
        private String icon;

        public String getIcon() {
            return icon;
        }
    }
}
