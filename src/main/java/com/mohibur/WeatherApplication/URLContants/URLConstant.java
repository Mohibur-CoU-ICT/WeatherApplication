package com.mohibur.WeatherApplication.URLContants;

public interface URLConstant {
    String API_PREFIX = "/api";
    String VERSION = "/v1";

    interface Weather {
        String ROOT = API_PREFIX + VERSION;
        String WEATHER = "/weather";
    }
}
