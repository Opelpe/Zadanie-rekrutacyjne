package com.pepe.rekrutacjagopos.data.remote.model.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsRetrofitResponse {

    @SerializedName("data")
    public List<ItemRetrofitModel> items;
}
