package com.pepe.rekrutacjagopos.di.module;


import com.pepe.rekrutacjagopos.App;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsService;
import com.pepe.rekrutacjagopos.data.remote.token.TokenService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final String TAG = NetworkModule.class.getSimpleName();

    public static final String BASE_URL = "https://demo2.gopos.pl";

    @Provides
    OkHttpClient providesOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    ItemsService providesItemsRetrofitService(OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ItemsService.class);
    }

    @Provides
    TokenService providesTokenService(OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TokenService.class);
    }
}
