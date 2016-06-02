package robin.com.weather.model;

import lombok.Getter;
import lombok.Setter;
import robin.com.weather.model.weather.Weather;

/**
 * Created by Rober on 02.06.2016.
 */
@Getter
@Setter
public class BusWeater {
    String locationName;
    String weatherText;
    int weatherIcon;
    Double temperatureInCelcius;
    String mobileLink;

    public BusWeater(Weather weather, String locationName) {
        this.locationName = locationName;
        weatherIcon = weather.getWeatherIcon();
        weatherText = weather.getWeatherText();
        temperatureInCelcius = weather.getTemperature().getMetric().getValue();
        mobileLink = weather.getMobileLink();
    }
}
