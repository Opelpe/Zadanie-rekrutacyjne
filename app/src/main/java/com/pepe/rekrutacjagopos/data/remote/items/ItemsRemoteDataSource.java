package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;
import com.pepe.rekrutacjagopos.data.remote.model.token.GetTokenResponse;
import com.pepe.rekrutacjagopos.data.remote.token.TokenService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsRemoteDataSource {


    public static final String TAG = ItemsRetrofitService.class.getSimpleName();
    private final ItemsRetrofitService itemsRetrofitService;
    private final int ORGANIZATION_ID = 27;

    private final TokenService tokenService;


    @Inject
    public ItemsRemoteDataSource(ItemsRetrofitService itemsRetrofitService, TokenService tokenService) {
        this.itemsRetrofitService = itemsRetrofitService;
        this.tokenService = tokenService;
    }

    public void getItems() {
        itemsRetrofitService.getItems(ORGANIZATION_ID).enqueue(new Callback<GetItemsRetrofitResponse>() {
            @Override
            public void onResponse(Call<GetItemsRetrofitResponse> call, Response<GetItemsRetrofitResponse> response) {
                Log.d(TAG, "et items, before response: " + response.code());
                GetItemsRetrofitResponse itemsResponse = response.body();

            }

            @Override
            public void onFailure(Call<GetItemsRetrofitResponse> call, Throwable t) {
                Log.e(TAG, "Get items, failure occurred: ", t);

            }
        });

    }
}
