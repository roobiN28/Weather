package robin.com.weather.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robin.com.weather.api.LocationService;
import robin.com.weather.api.WeatherService;
import robin.com.weather.model.BusError;
import robin.com.weather.model.BusLocation;
import robin.com.weather.model.BusWeater;
import robin.com.weather.model.BusWeatherBitmap;
import robin.com.weather.model.location.Location;
import robin.com.weather.model.weather.Weather;
import robin.com.weather.parser.UrlParser;

/**
 * Created by Rober on 02.06.2016.
 */

public class RequestServiceImpl implements RequestService{
    private Context context;
    private Bus bus = BusProvider.getInstance().getBus();
    private Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit();

    public RequestServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void localization(final String text) {
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

    @Override
    public void bitmap(final int weatherIconNumber) {
        String path = new UrlParser().generateURL(weatherIconNumber);
        Log.d("TAG", path);
        Picasso.with(context).load(path).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bus.post(new BusWeatherBitmap(bitmap,weatherIconNumber) );
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                bus.post(new BusError("Loading image error", errorDrawable.toString() ) );
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
