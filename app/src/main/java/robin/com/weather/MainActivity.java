package robin.com.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import robin.com.weather.model.BusError;
import robin.com.weather.model.BusLocation;
import robin.com.weather.model.BusWeater;
import robin.com.weather.parser.UrlParser;
import robin.com.weather.service.BusProvider;
import robin.com.weather.service.RequestServiceImpl;
import robin.com.weather.service.RetrofitProvider;

public class MainActivity extends AppCompatActivity {
    private Bus bus = BusProvider.getInstance().getBus();
    Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit();
    boolean isFindedLocation;
    @BindView(R.id.editTextCity) EditText city;
    @BindView(R.id.textView2) TextView textViewTemp;
    @BindView(R.id.textView3) TextView textViewWeather;
    @BindView(R.id.textView4) TextView textViewCity;
    @BindView(R.id.buttonSubmit) Button submit;
    @BindView(R.id.imageViewWeatherIcon) ImageView imageView;
    RequestServiceImpl service = new RequestServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bus.register(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                service.localization(city.getText().toString(),textViewCity);

            }
        });
    }

    @Subscribe
    public void onLocationHandler(ArrayList<BusLocation> event) {
        textViewCity.setText(event.get(0).getRegionName());
        service.weather(event.get(0).getKey());
    }
    @Subscribe
    public void onWeatherEvent(BusWeater weather) {
        textViewTemp.setText(weather.getTemperatureInCelcius().toString());
        String path = new UrlParser().generateURL(weather.getWeatherIcon());
        Log.d("TAG", path);
        Picasso.with(getBaseContext()).load(path).into(imageView);
    }
    @Subscribe
    public void onRequstError(BusError error) {
        Toast.makeText(getApplicationContext(),error.getName(),Toast.LENGTH_SHORT);
    }


    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }




}
