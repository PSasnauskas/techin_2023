package lt.techin.zoo.api.dto;

import java.time.LocalDateTime;

public class WeatherDto {
    private String name;
    private WeatherMainDto main;

    private LocalDateTime lastUpdatedLocalTime;

    private WeatherWindDto wind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherMainDto getMain() {
        return main;
    }

    public void setMain(WeatherMainDto main) {
        this.main = main;
    }

    public WeatherWindDto getWind() {
        return wind;
    }

    public void setWind(WeatherWindDto wind) {
        this.wind = wind;
    }

    public LocalDateTime getLastUpdatedLocalTime() {
        return lastUpdatedLocalTime;
    }

    public void setLastUpdatedLocalTime(LocalDateTime lastUpdatedLocalTime) {
        this.lastUpdatedLocalTime = lastUpdatedLocalTime;
    }

}
