package robin.com.weather;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import robin.com.weather.api.LocationService;
import robin.com.weather.api.RetrofitService;
import robin.com.weather.model.location.Location;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test // throw apikey by OkHttp
    public void testRetrofit() {
        Retrofit retrofit =  RetrofitService.getInstance().getRetrofit();

        LocationService locationService =  retrofit.create(LocationService.class);
        Call<List<Location>> call = locationService.findLocation("Strzyżów");

        try {
            Response<List<Location>> res = call.execute();
            Location l = res.body().get(0);
            System.out.println(l.country.localizedName);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}