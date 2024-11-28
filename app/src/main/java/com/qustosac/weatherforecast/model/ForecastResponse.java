package com.qustosac.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponse {

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private float message;

    @SerializedName("cnt")
    private int cnt;

    @SerializedName("list")
    private List<Forecast> list;

    @SerializedName("city")
    private City city;

    public String getCod() {
        return cod;
    }

    public float getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<Forecast> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public static class Forecast {

        @SerializedName("dt")
        private long dt;

        @SerializedName("main")
        private Main main;

        @SerializedName("weather")
        private List<Weather> weather;

        @SerializedName("clouds")
        private Clouds clouds;

        @SerializedName("wind")
        private Wind wind;

        @SerializedName("visibility")
        private int visibility;

        @SerializedName("pop")
        private float pop;

        @SerializedName("sys")
        private Sys sys;

        @SerializedName("dt_txt")
        private String dtTxt;

        public long getDt() {
            return dt;
        }

        public Main getMain() {
            return main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public int getVisibility() {
            return visibility;
        }

        public float getPop() {
            return pop;
        }

        public Sys getSys() {
            return sys;
        }

        public String getDtTxt() {
            return dtTxt;
        }

        public static class Main {

            @SerializedName("temp")
            private float temp;

            @SerializedName("feels_like")
            private float feelsLike;

            @SerializedName("temp_min")
            private float tempMin;

            @SerializedName("temp_max")
            private float tempMax;

            @SerializedName("pressure")
            private int pressure;

            @SerializedName("sea_level")
            private int seaLevel;

            @SerializedName("grnd_level")
            private int grndLevel;

            @SerializedName("humidity")
            private int humidity;

            @SerializedName("temp_kf")
            private float tempKf;

            public float getTemp() {
                return temp;
            }

            public float getFeelsLike() {
                return feelsLike;
            }

            public float getTempMin() {
                return tempMin;
            }

            public float getTempMax() {
                return tempMax;
            }

            public int getPressure() {
                return pressure;
            }

            public int getSeaLevel() {
                return seaLevel;
            }

            public int getGrndLevel() {
                return grndLevel;
            }

            public int getHumidity() {
                return humidity;
            }

            public float getTempKf() {
                return tempKf;
            }
        }

        public static class Weather {

            @SerializedName("id")
            private int id;

            @SerializedName("main")
            private String main;

            @SerializedName("description")
            private String description;

            @SerializedName("icon")
            private String icon;

            public int getId() {
                return id;
            }

            public String getMain() {
                return main;
            }

            public String getDescription() {
                return description;
            }

            public String getIcon() {
                return icon;
            }
        }

        public static class Clouds {

            @SerializedName("all")
            private int all;

            public int getAll() {
                return all;
            }
        }

        public static class Wind {

            @SerializedName("speed")
            private float speed;

            @SerializedName("deg")
            private int deg;

            @SerializedName("gust")
            private float gust;

            public float getSpeed() {
                return speed;
            }

            public int getDeg() {
                return deg;
            }

            public float getGust() {
                return gust;
            }
        }

        public static class Sys {

            @SerializedName("pod")
            private String pod;

            public String getPod() {
                return pod;
            }
        }
    }

    public static class City {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("coord")
        private Coord coord;

        @SerializedName("country")
        private String country;

        @SerializedName("population")
        private int population;

        @SerializedName("timezone")
        private int timezone;

        @SerializedName("sunrise")
        private long sunrise;

        @SerializedName("sunset")
        private long sunset;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Coord getCoord() {
            return coord;
        }

        public String getCountry() {
            return country;
        }

        public int getPopulation() {
            return population;
        }

        public int getTimezone() {
            return timezone;
        }

        public long getSunrise() {
            return sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public static class Coord {

            @SerializedName("lat")
            private float lat;

            @SerializedName("lon")
            private float lon;

            public float getLat() {
                return lat;
            }

            public float getLon() {
                return lon;
            }
        }
    }
}