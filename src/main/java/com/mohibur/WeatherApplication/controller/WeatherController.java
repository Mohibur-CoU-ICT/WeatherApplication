package com.mohibur.WeatherApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohibur.WeatherApplication.URLContants.URLConstant.Weather;
import com.mohibur.WeatherApplication.dto.SysDto;
import com.mohibur.WeatherApplication.dto.WeatherDto;
import com.mohibur.WeatherApplication.entity.City;
import com.mohibur.WeatherApplication.entity.WeatherResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequestMapping(value = Weather.ROOT)
public class WeatherController {

    @GetMapping(value = Weather.WEATHER)
    public Map<String, City> getWeatherByCity(@RequestParam(name = "city") String city) {
        Map<String, City> map = new HashMap<>();
        try {
            String s = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=YOUR_APP_ID_HERE";
            URL url = new URL(s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "PostmanRuntime/7.29.2");
//			con.setRequestProperty("User-Agent", "Chrome/95.0.4638.69");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner sc = new Scanner(url.openStream());
                String inline = "";
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();

                ObjectMapper objectMapper = new ObjectMapper();
                WeatherResponse weatherResponse = objectMapper.readValue(inline, WeatherResponse.class);

                WeatherDto weatherDto = new WeatherDto();
                SysDto sysDto = new SysDto();
                BeanUtils.copyProperties(weatherResponse.getMain(), weatherDto);
                BeanUtils.copyProperties(weatherResponse.getSys(), sysDto);

                City city1 = new City();
                city1.setWeather(weatherDto);
                city1.setSys(sysDto);

                map.put(city, city1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
