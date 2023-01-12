package lt.techin.zoo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.techin.zoo.configuration.OpenWeatherApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_MAP_API_GET_FORMAT = "http://api.openweathermap.org/data/2.5/weather?id=%s&APPID=%s&units=%s";

    public static Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final String requestURL;
    private final ObjectMapper objectMapper;

    public WeatherService(OpenWeatherApiProperties weatherApiProperties, ObjectMapper objectMapper) {
        this.requestURL = String.format(OPEN_WEATHER_MAP_API_GET_FORMAT,
                weatherApiProperties.getCityId(), weatherApiProperties.getApplicationId(), weatherApiProperties.getUnits());
        this.objectMapper = objectMapper;
    }

    //@Scheduled(fixedRateString = "${weather.api.delay-milliseconds}")
    public void updateWeatherInformation() {
        logger.info("Calling for weather Information");

        //TODO debug
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(requestURL, String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            var responseBody = response.getBody();
        } else {
            logger.error("There was an error updating Weather Info. Request status code: {}", response.getStatusCode());
        }

        logger.info("Open Weather API response: {}", response);
    }


}
