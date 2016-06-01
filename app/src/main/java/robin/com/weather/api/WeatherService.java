package robin.com.weather.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import robin.com.weather.model.weather.Weather;

/**
 * Created by Rober on 30.05.2016.
 */
public interface WeatherService {

    @GET("currentconditions/v1/{locationKey}")
    Call<List<Weather>> getWeather(@Path("locationKey") String locationKey);

}
