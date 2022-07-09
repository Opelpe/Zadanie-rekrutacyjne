package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.google.gson.Gson;
import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;
import com.pepe.rekrutacjagopos.data.remote.model.token.GetTokenResponse;
import com.pepe.rekrutacjagopos.data.remote.token.TokenService;

import java.util.EventListener;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsRemoteDataSource {


    public static final String TAG = ItemsRetrofitService.class.getSimpleName();
    private final ItemsRetrofitService itemsRetrofitService;
    private final int ORGANIZATION_ID = 27;

    private final TokenService tokenService;

    private final String password = "zadanie";
    private final String clientSecret = "dc6d8a5e-861b-4df8-bb6b-9889c106161d";
    private final String clientID = "073481d0-549e-4eac-9174-27cd2432f149";
    private final String login = "zadanie@zadanie.com";
    private ItemsListener itemsListener;

    public interface ItemsListener {
        void onItemsLoaded(GetItemsRetrofitResponse retrofitResponse);
    }

    @Inject
    public ItemsRemoteDataSource(ItemsRetrofitService itemsRetrofitService, TokenService tokenService) {
        this.itemsRetrofitService = itemsRetrofitService;
        this.tokenService = tokenService;
    }

    public void getItems() {

        tokenService.getToken(password, "password", clientSecret, clientID, login).enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {

                Log.d(TAG, "On items before TOKEN response: " + response.body().token);
//                response.body().type
                Gson gson = new Gson();

                Log.d(TAG, "On items before ITEMS response: " + response.body().token);
                itemsRetrofitService.getItems("Bearer " + response.body().token, ORGANIZATION_ID).enqueue(new Callback<GetItemsRetrofitResponse>() {
                    @Override
                    public void onResponse(Call<GetItemsRetrofitResponse> call, Response<GetItemsRetrofitResponse> response) {
                        Log.d(TAG, "On items before response: " + response.code());
                        GetItemsRetrofitResponse retrofitResponse = response.body();
                        Log.d(TAG, "On items after response: " + retrofitResponse.items.size());

                        if (retrofitResponse != null){
                            itemsListener.onItemsLoaded(response.body());
                        }



                    }

                    @Override
                    public void onFailure(Call<GetItemsRetrofitResponse> call, Throwable throwable) {
                        Log.d(TAG, "On items after response FAILURE: " + throwable);
                    }
                });
//            itemsRetrofitService.getItems("Bearer " + response.body().token, ORGANIZATION_ID).enqueue(new Callback<GetItemsRetrofitResponse>() {
//                @Override
//                public void onResponse(Call<GetItemsRetrofitResponse> call, Response<GetItemsRetrofitResponse> response1) {
//                    Log.d(TAG, "On items before response: " + response1.body().items);
//                }
//
//                @Override
//                public void onFailure(Call<GetItemsRetrofitResponse> call, Throwable throwable) {
//                    Log.d(TAG, "On items after response FAILURE: " + throwable);
//                }
//            });
//                itemsRetrofitService.getItems("Bearer " + response.body().token, ORGANIZATION_ID).enqueue(new Callback<GetItemsRetrofitResponse>() {
//                    @Override
//                    public void onResponse(Call<GetItemsRetrofitResponse> call, Response<GetItemsRetrofitResponse> response1) {
////                        Log.d(TAG, "On items before response: " + response.code());
////                        GetItemsRetrofitResponse[] itemsRetrofitResponse = gson
////                                .fromJson(response1.body(), itemsRetrofitResponse);
////                        response1.body().items.get().name;
////                        Log.d(TAG, "On items after response: " + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<GetItemsRetrofitResponse> call, Throwable throwable) {
//                        Log.d(TAG, "On items after response FAILURE: " + throwable);
//                    }
//                });
            }

            @Override
            public void onFailure(Call<GetTokenResponse> call, Throwable throwable) {
                Log.d(TAG, "TOKEN after response FAILURE: " + throwable + throwable.getLocalizedMessage());
            }
        });


    }
}
