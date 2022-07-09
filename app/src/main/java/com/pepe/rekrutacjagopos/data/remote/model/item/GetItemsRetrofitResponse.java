package com.pepe.rekrutacjagopos.data.remote.model.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemsRetrofitResponse {

    @SerializedName("data")
    public List<ItemRetrofitModel> items;


}
