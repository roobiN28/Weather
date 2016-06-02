package robin.com.weather.controller;

import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robin.com.weather.api.LocationService;
import robin.com.weather.api.RetrofitService;
import robin.com.weather.model.location.Location;

/**
 * Created by Rober on 02.06.2016.
 */

public class MainController {

    Retrofit retrofit = RetrofitService.getInstance().getRetrofit();

    public void localization(final String text, final TextView textView) {
        LocationService locationService = retrofit.create(LocationService.class);
        Call<List<Location>> call = locationService.findLocation(text);
        Location loc;
        call.enqueue(new Callback<List<robin.com.weather.model.location.Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(! response.body().isEmpty()) {
                    textView.setText(response.body().get(0).region.localizedName);
                } else {
                    textView.setText("Nie znaleziono lokalizacji.");
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                textView.setText("Błąd rządania");
            }
        });
    }
}
