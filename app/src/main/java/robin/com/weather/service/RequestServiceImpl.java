package robin.com.weather.service;

import android.util.Log;
import android.widget.TextView;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robin.com.weather.api.LocationService;
import robin.com.weather.api.WeatherService;
import robin.com.weather.model.BusError;
import robin.com.weather.model.BusWeater;
import robin.com.weather.model.location.Location;
import robin.com.weather.model.BusLocation;
import robin.com.weather.model.weather.Weather;

/**
 * Created by Rober on 02.06.2016.
 */

public class RequestServiceImpl implements RequestService{
    Bus bus = BusProvider.getInstance().getBus();
    Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit();

    @Override
    public void localization(final String text, final TextView textView) {
        LocationService locationService = retrofit.create(LocationService.class);
        Call<List<Location>> call = locationService.findLocation(text);
        call.enqueue(new Callback<List<robin.com.weather.model.location.Location>>() {
            Bus bus = BusProvider.getInstance().getBus();
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(! response.body().isEmpty()) {
                    List<BusLocation> listLocationBus = new ArrayList<BusLocation>();
                    for (Location l : response.body()) {
                        listLocationBus.add(new BusLocation(l));
                    }
                    bus.post(listLocationBus);
                    Log.d("TAG", "ilosc wynikow: "+ listLocationBus.size());
                } else {
                    bus.post(new BusError("Empty Response", "Response is empty"));
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                bus.post(new BusError("Call failure",t.getMessage()));
            }
        });
    }

    @Override
    public void weather(final String key) {
        Call<List<Weather>> call = retrofit.create(WeatherService.class).getWeather(key);
        call.enqueue(new Callback<List<Weather>>() {
            Bus bus = BusProvider.getInstance().getBus();
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                bus.post(new BusWeater(response.body().get(0),key));
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                bus.post(new BusError("Call failure", t.getMessage()));
            }
        });
    }
}
