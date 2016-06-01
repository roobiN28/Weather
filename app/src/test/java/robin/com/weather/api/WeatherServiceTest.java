package robin.com.weather.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import robin.com.weather.model.weather.Weather;

/**
 * Created by Rober on 30.05.2016.
 */
public class WeatherServiceTest {
    WeatherService weatherService;
    @Before
    public void setUp() {
        Retrofit retrofit =  RetrofitService.getInstance().getRetrofit();

        weatherService =  retrofit.create(WeatherService.class);

    }

    @Test
    public void testGetWeather() throws Exception {
        Call<List<Weather>> call = weatherService.getWeather("265535");
        Response<List<Weather>> response = call.execute();
        Weather weather = response.body().get(0);
        System.out.println(weather.getTemperature().getMetric().getValue());
    }

}