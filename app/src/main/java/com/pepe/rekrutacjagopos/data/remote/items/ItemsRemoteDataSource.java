package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import androidx.annotation.NonNull;

import com.pepe.rekrutacjagopos.data.remote.model.item.ItemsRetrofitResponse;
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
    private static DataListener dataListener;

    private final ItemsService itemsService;
    private final TokenService tokenService;
    private final int ORGANIZATION_ID = 27;

    public interface DataListener {
        void onResponse(List<ItemRetrofitModel> retrofitResponse);
    }

    public void setDataListener(DataListener dataListener) {
        ItemsRemoteDataSource.dataListener = dataListener;
    }

    @Inject
    public ItemsRemoteDataSource(ItemsService itemsService, TokenService tokenService) {
        this.itemsService = itemsService;
        this.tokenService = tokenService;
    }

    public void getRemoteItems() {

        String clientSecret = "dc6d8a5e-861b-4df8-bb6b-9889c106161d";
        String password = "zadanie";
        String clientID = "073481d0-549e-4eac-9174-27cd2432f149";
        String login = "zadanie@zadanie.com";

        tokenService.getToken(password, "password", clientSecret, clientID, login).enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(@NonNull Call<TokenModel> call, @NonNull Response<TokenModel> response) {

                itemsService.getItems(response.body().type + response.body().token, ORGANIZATION_ID).enqueue(new Callback<ItemsRetrofitResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ItemsRetrofitResponse> call, @NonNull Response<ItemsRetrofitResponse> response) {
                        Log.d(TAG, "Get items Retrofit response: " + response.code());
                        ItemsRetrofitResponse retrofitResponse = response.body();

                            dataListener.onResponse(retrofitResponse.items);

                    }
                    @Override
                    public void onFailure(@NonNull Call<ItemsRetrofitResponse> call, @NonNull Throwable throwable) {
                        Log.d(TAG, "Get items Retrofit response FAILURE: " + throwable);
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
