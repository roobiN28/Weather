package robin.com.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robin.com.weather.api.LocationService;
import robin.com.weather.api.RetrofitService;
import robin.com.weather.controller.MainController;
import robin.com.weather.model.location.Location;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit = RetrofitService.getInstance().getRetrofit();
    boolean isFindedLocation;
    @BindView(R.id.editTextCity) EditText city;
    @BindView(R.id.textView2) TextView textViewTemp;
    @BindView(R.id.textView3) TextView textViewWeather;
    @BindView(R.id.textView4) TextView textViewCity;
    @BindView(R.id.buttonSubmit) Button submit;
    @BindView(R.id.imageViewWeatherIcon) ImageView imageView;
    MainController controller = new MainController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                controller.localization(city.getText().toString(),textViewCity);

            }
        });
    }

    private void findLocation(String location) {
        Call<List<Location>> call = retrofit.create(LocationService.class).findLocation(location);
        call.enqueue(new Callback<List<Location>>() {
            Location location;
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(response.body().isEmpty()) {
                    isFindedLocation = false;
                    Toast.makeText(getApplicationContext(),"Nie znaleziono miasta", Toast.LENGTH_SHORT).show();
                } else {
                    isFindedLocation = true;
                    location = response.body().get(0);
                }
                textViewCity.setText(location.geoPosition.latitude+" | "+location.geoPosition.longitude);
                textViewTemp.setText(response.body().size());
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Błąd rzadania", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
