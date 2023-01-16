package lt.techin.zoo.api;

import lt.techin.zoo.api.dto.WeatherDto;
import lt.techin.zoo.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    //@Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    @ResponseBody
    public WeatherDto getWeatherInfo() {
        return weatherService.getLastWeather().get();
    }

}
