package com.bagoes.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuacaListModel
{
    @SerializedName("main")
    private CuacaMainModel cuacaMainModel;

    @SerializedName("weather")
    private List<WeatherModel> weatherModelList;
    private String dt_txt;

    public CuacaListModel() {}

    public CuacaMainModel getCuacaMainModel() {
        return cuacaMainModel;
    }

    public List<WeatherModel> getWeatherModelList() {
        return weatherModelList;
    }

    public void setWeatherModelList(List<WeatherModel> weatherModelList) {
        this.weatherModelList = weatherModelList;
    }

    public void setCuacaMainModel(CuacaMainModel cuacaMainModel) {
        this.cuacaMainModel = cuacaMainModel;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
