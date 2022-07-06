package com.pepe.rekrutacjagopos.data.remote.items;

import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemsRetrofitService {

    @GET("api/v3/{organizationId}/items")
    Call<GetItemsRetrofitResponse> getItems(@Path("organizationId") int organizationId);

}