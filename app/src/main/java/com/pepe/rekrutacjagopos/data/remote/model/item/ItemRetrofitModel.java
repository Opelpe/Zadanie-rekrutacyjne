package com.pepe.rekrutacjagopos.data.remote.model.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemRetrofitModel {

    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public ItemPriceModel price;

    @SerializedName("category")
    public CategoryModel category;

    @SerializedName("tax_id")
    public int tax;
}
