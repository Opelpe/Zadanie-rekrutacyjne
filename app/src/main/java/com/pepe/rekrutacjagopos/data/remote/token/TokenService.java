package com.pepe.rekrutacjagopos.data.remote.token;

import com.pepe.rekrutacjagopos.data.remote.model.token.TokenModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TokenService {

    @GET("/oauth/token?")
    Call<TokenModel> getToken(
            @Query("password") String password,
            @Query("grant_type") String type,
            @Query("client_secret") String clientSecret,
            @Query("client_id") String clientID,
            @Query("username") String login);

}

