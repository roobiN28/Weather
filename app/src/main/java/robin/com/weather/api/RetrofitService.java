package robin.com.weather.api;

import java.io.IOException;

import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rober on 30.05.2016.
 */
public class RetrofitService {



    private static RetrofitService instance = new RetrofitService();

    public static RetrofitService getInstance() {
        return instance;
    }
    //LRmp6xL2GHnHzhYuflvauDfaYBol85hX
    private final String API_KEY = "LRmp6xL2GHnHzhYuflvauDfaYBol85hX";
    @Getter
    private Retrofit retrofit ;

    private RetrofitService() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("apikey",API_KEY)
                        .addQueryParameter("language","pl-pl").build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }



}
