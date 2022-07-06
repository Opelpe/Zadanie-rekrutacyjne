package com.pepe.rekrutacjagopos.data.remote.token;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.remote.items.ItemsRetrofitService;

import javax.inject.Inject;

public class TokenRemoteDataSource {

    public static final String TAG = ItemsRetrofitService.class.getSimpleName();
    private final TokenService tokenService;

    @Inject
    public TokenRemoteDataSource(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void getToken() {
        Log.d(TAG, "GET TOKEN, Before response: ");
//        tokenService.getToken(password, clientSecret, clientID, login).enqueue(new Callback<GetTokenResponse>() {
//            @Override
//            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
//                Log.d(TAG, "Before response: " + response.code());
////                GetTokenResponse tokenResponse = response.body();
////                Log.d(TAG, "Token response: " + tokenResponse.toString());
//            }
//
//            @Override
//            public void onFailure(Call<GetTokenResponse> call, Throwable t) {
//                Log.e(TAG, "On failure ", t);
//
//            }
//        });
    }
}
