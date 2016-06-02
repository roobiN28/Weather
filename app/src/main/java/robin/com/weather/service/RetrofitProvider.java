package robin.com.weather.service;

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
public class RetrofitProvider {



    private static RetrofitProvider instance = new RetrofitProvider();

    public static RetrofitProvider getInstance() {
        return instance;
    }

    private final String API_KEY = "LRmp6xL2GHnHzhYuflvauDfaYBol85hX";
    @Getter
    private Retrofit retrofit ;

    private RetrofitProvider() {

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
