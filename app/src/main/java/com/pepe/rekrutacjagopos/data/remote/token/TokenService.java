package com.pepe.rekrutacjagopos.data.remote.token;

import com.pepe.rekrutacjagopos.data.remote.model.token.GetTokenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TokenService {

    @GET("/oauth/token?")
    Call<Void> getToken(
            @Query("password") String password,
            @Query("grant_type") String type,
            @Query("client_secret") String clientSecret,
            @Query("client_id") String clientID,
            @Query("login") String login);

}

