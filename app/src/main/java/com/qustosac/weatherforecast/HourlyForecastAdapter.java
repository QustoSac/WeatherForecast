package com.qustosac.weatherforecast;

import static java.lang.Math.round;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.qustosac.weatherforecast.R;
import com.qustosac.weatherforecast.model.ForecastResponse;

import java.util.ArrayList;
import java.util.List;


public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ForecastViewHolder> {

    private List<ForecastResponse.Forecast> forecastList = new ArrayList<>();

    public void setForecastList(List<ForecastResponse.Forecast> forecastList) {
        this.forecastList = forecastList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastResponse.Forecast forecast = forecastList.get(position);
        holder.time.setText(forecast.getDtTxt());
        double tempInCelsius = forecast.getMain().getTemp() - 273.15;
        holder.temperature.setText(String.format("%.1f°C", tempInCelsius));

    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView time, temperature;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            temperature = itemView.findViewById(R.id.temperature);
        }
    }
}

