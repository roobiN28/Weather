package robin.com.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robin.com.weather.api.LocationService;
import robin.com.weather.api.RetrofitService;
import robin.com.weather.api.WeatherService;
import robin.com.weather.model.location.Location;
import robin.com.weather.model.weather.Weather;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editTextCity) EditText city;
    @BindView(R.id.textView2) TextView textViewTemp;
    @BindView(R.id.textView3) TextView textViewWeather;
    @BindView(R.id.textView4) TextView country;
    @BindView(R.id.buttonSubmit) Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWeather("265535");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                final LocationService locationService = RetrofitService.getInstance().getRetrofit().create(LocationService.class);
                Call<List<Location>> callLocation = locationService.findLocation(city.getText().toString());
                callLocation.enqueue(new Callback<List<Location>>() {
                    private Location location;

                    @Override
                    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                        location = response.body().get(0);

                        setCountry(location.country.localizedName);
                        getWeather(location.key);





                    }

                    @Override
                    public void onFailure(Call<List<Location>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Nie znaleziono miasta ",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    private void setCountry ( String name) {
        country.setText(name);
    }
    private void setTemperature(String temperature) {
        textViewTemp.setText(temperature);
    }
    private void setWeatherText(String weatherText) {
        textViewWeather.setText(weatherText);
    }
    private void getWeather(String key) {
        WeatherService weatherService = RetrofitService.getInstance().getRetrofit().create(WeatherService.class);
        Call<List<Weather>> callWeather = weatherService.getWeather(key);
        callWeather.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                Weather w = response.body().get(0);
                setTemperature(w.getTemperature().getMetric().getValue().toString());
                setWeatherText(w.getWeatherText());
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {

            }
        });
    }


}
