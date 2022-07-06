package com.pepe.rekrutacjagopos.di.module;


import com.pepe.rekrutacjagopos.data.remote.ItemsRetrofitService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    OkHttpClient providesOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    ItemsRetrofitService providesItemsRetrofitService(OkHttpClient client){
        //TODO add

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend.example.com")
                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ItemsRetrofitService.class);
    }
}
