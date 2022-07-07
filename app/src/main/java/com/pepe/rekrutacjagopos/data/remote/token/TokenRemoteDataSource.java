package com.pepe.rekrutacjagopos.data.remote.token;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.remote.items.ItemsRetrofitService;
import com.pepe.rekrutacjagopos.data.remote.model.token.GetTokenResponse;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenRemoteDataSource {

    public static final String TAG = ItemsRetrofitService.class.getSimpleName();
    private final TokenService tokenService;

    private final String password = "zadanie";
    private final String clientSecret = "dc6d8a5e-861b-4df8-bb6b-9889c106161d";
    private final String clientID = "073481d0-549e-4eac-9174-27cd2432f149";
    private final String login = "zadanie@zadanie.com";

    @Inject
    public TokenRemoteDataSource(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    public void getToken() {
        tokenService.getToken(password, "password", clientSecret, clientID, login).enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
                GetTokenResponse getTokenResponse = response.body();
                Log.d(TAG, "Token response: " + getTokenResponse.token);
            }

            @Override
            public void onFailure(Call<GetTokenResponse> call, Throwable t) {

            }
        });
    }
}
