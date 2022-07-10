package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import androidx.annotation.NonNull;

import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;
import com.pepe.rekrutacjagopos.data.remote.model.token.TokenModel;
import com.pepe.rekrutacjagopos.data.remote.token.TokenService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsRemoteDataSource {
    private static final String TAG = ItemsService.class.getSimpleName();

    private final ItemsService itemsService;
    private final TokenService tokenService;
    private final int ORGANIZATION_ID = 27;

    private final String password = "zadanie";
    private final String clientSecret = "dc6d8a5e-861b-4df8-bb6b-9889c106161d";
    private final String clientID = "073481d0-549e-4eac-9174-27cd2432f149";
    private final String login = "zadanie@zadanie.com";

    private dataListener dataListener;

    public interface dataListener {
        void onItemsLoaded(List<ItemRetrofitModel> retrofitResponse);
    }

    public void setItemsListener(dataListener dataListener) {
        this.dataListener = dataListener;
    }

    @Inject
    public ItemsRemoteDataSource(ItemsService itemsService, TokenService tokenService) {
        this.itemsService = itemsService;
        this.tokenService = tokenService;
    }

    public void getItems() {

        tokenService.getToken(password, "password", clientSecret, clientID, login).enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(@NonNull Call<TokenModel> call, @NonNull Response<TokenModel> response) {

                Log.d(TAG, "GET ITEMS TOKEN response: " + response.body().token);

                itemsService.getItems(response.body().type + response.body().token, ORGANIZATION_ID).enqueue(new Callback<GetItemsRetrofitResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GetItemsRetrofitResponse> call, @NonNull Response<GetItemsRetrofitResponse> response) {
                        Log.d(TAG, "On items Retrofit response: " + response.code());
                        GetItemsRetrofitResponse retrofitResponse = response.body();

                        if (retrofitResponse != null) {

                            dataListener.onItemsLoaded(retrofitResponse.items);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GetItemsRetrofitResponse> call, @NonNull Throwable throwable) {
                        Log.d(TAG, "On items Retrofit response FAILURE: " + throwable);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<TokenModel> call, @NonNull Throwable throwable) {
                Log.d(TAG, "GET TOKEN response FAILURE: " + throwable);
            }
        });
    }
}
