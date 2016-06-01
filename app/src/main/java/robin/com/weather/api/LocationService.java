package robin.com.weather.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import robin.com.weather.model.location.Location;

/**
 * Created by Rober on 30.05.2016.
 */
public interface LocationService {

    @GET("locations/v1/search")
    Call<List<Location>> findLocation(@Query(value = "q", encoded = true) String location);

}
