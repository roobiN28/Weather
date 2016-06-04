package robin.com.weather;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import robin.com.weather.model.BusError;
import robin.com.weather.model.BusLocation;
import robin.com.weather.model.BusWeater;
import robin.com.weather.model.BusWeatherBitmap;
import robin.com.weather.service.BusProvider;
import robin.com.weather.service.RequestService;
import robin.com.weather.service.RequestServiceImpl;
import robin.com.weather.service.RetrofitProvider;


public class MainActivity extends AppCompatActivity {
    private Bus bus = BusProvider.getInstance().getBus();
    Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit();
    RequestService service;

    @BindView(R.id.editTextCity) EditText miasto;
    @BindView(R.id.textViewTemperature) TextView textViewTemp;
    @BindView(R.id.textViewWeatherDescription) TextView textViewWeather;
    @BindView(R.id.listaLokacji)  ListView listView;
    @BindView(R.id.buttonSubmit) Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bus.register(this);
        service = new RequestServiceImpl(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                service.localization(miasto.getText().toString());

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listView.getItemAtPosition(position);
                String key = item.split(", ")[0];
                service.weather(key);
            }
        });
    }

    @Subscribe
    public void onLocationHEvent(ArrayList<BusLocation> event) {
        List<String> items = new ArrayList<>();
        for(BusLocation loc : event) {
            items.add(loc.getKey()+ ", " +loc.getCoutryName()+", "+loc.getAdministrativeArea() +", " +loc.getNearBy());
        }
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text2 ,items);
        listView.setAdapter(simpleAdapter);
    }
    @Subscribe
    public void onWeatherEvent(BusWeater weather) {
        textViewTemp.setText(weather.getTemperatureInCelcius().toString());
        textViewWeather.setText(weather.getWeatherText());
        service.bitmap(weather.getWeatherIcon());

    }
    @Subscribe
    public void onBitmapEvent(BusWeatherBitmap busWeatherBitmap) {
        textViewWeather.setBackground(new BitmapDrawable(getApplicationContext().getResources(), busWeatherBitmap.getBitmap()));
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
