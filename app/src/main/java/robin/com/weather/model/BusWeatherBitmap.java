package robin.com.weather.model;

import android.graphics.Bitmap;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Rober on 04.06.2016.
 */
@Getter @Setter
public class BusWeatherBitmap {
    int weatherIconNumber;
    private Bitmap bitmap;

    public BusWeatherBitmap(Bitmap b) {
        bitmap = b;
    }
    public BusWeatherBitmap(Bitmap b, int number) {
        weatherIconNumber = number;
        bitmap = b;
    }
}
