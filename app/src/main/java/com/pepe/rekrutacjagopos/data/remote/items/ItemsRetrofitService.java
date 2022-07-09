package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ItemsRetrofitService {

    @GET("api/v3/{organizationId}/items")
    Call<GetItemsRetrofitResponse> getItems(@Header("Authorization") String token, @Path("organizationId") int organizationId);}