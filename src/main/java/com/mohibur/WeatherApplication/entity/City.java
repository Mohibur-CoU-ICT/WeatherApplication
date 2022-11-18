package com.mohibur.WeatherApplication.entity;

import com.mohibur.WeatherApplication.dto.SysDto;
import com.mohibur.WeatherApplication.dto.WeatherDto;

public class City {
    WeatherDto weather;
    SysDto sys;

    public WeatherDto getWeather() {
        return weather;
    }

    public void setWeather(WeatherDto weather) {
        this.weather = weather;
    }

    public SysDto getSys() {
        return sys;
    }

    public void setSys(SysDto sys) {
        this.sys = sys;
    }
}
