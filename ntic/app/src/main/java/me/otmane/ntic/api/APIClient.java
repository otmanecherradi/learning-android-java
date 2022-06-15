package me.otmane.ntic.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient instance = null;

    private final APIInterface apiInterface;

    private APIClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(APIInterface.class);
    }

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public APIInterface getAPI() {
        return apiInterface;
    }
}
