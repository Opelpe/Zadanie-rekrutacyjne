package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;
import com.pepe.rekrutacjagopos.data.remote.token.TokenService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsRemoteDataSource {


    public static final String TAG = ItemsRetrofitService.class.getSimpleName();
    private final ItemsRetrofitService itemsRetrofitService;
    private final int ORGANIZATION_ID = 27;

    private final String password = "zadanie";
    private final String clientSecret = "dc6d8a5e-861b-4df8-bb6b-9889c106161d";
    private final String clientID = "073481d0-549e-4eac-9174-27cd2432f149";
    private final String login = "zadanie@zadanie.com";
    private final TokenService tokenService;


    @Inject
    public ItemsRemoteDataSource(ItemsRetrofitService itemsRetrofitService, TokenService tokenService) {
        this.itemsRetrofitService = itemsRetrofitService;
        this.tokenService = tokenService;
    }

    public void getItems() {
        tokenService.getToken(password, "password", clientSecret, clientID, login).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "On token RESPONSE: " + response.code());
                Log.d(TAG, "On token RESPONSE MSG: " + response.message());
                Log.d(TAG, "On token RESPONSE RAW: " + response.raw());

                itemsRetrofitService.getItems(ORGANIZATION_ID).enqueue(new Callback<GetItemsRetrofitResponse>() {
                    @Override
                    public void onResponse(Call<GetItemsRetrofitResponse> call, Response<GetItemsRetrofitResponse> response) {
                        Log.d(TAG, "before response: " + response.code());
                        GetItemsRetrofitResponse itemsResponse = response.body();
                        Log.d(TAG, "after response: ");

                    }

                    @Override
                    public void onFailure(Call<GetItemsRetrofitResponse> call, Throwable t) {
                        Log.e(TAG, "On failure ", t);

                    }
                });
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}
