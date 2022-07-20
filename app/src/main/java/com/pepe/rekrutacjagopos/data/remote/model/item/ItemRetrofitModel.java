package com.pepe.rekrutacjagopos.data.remote.model.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


public class ItemRetrofitModel {


    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public ItemPriceModel price;

    @SerializedName("category_id")
    public int category;

    @SerializedName("tax_id")
    public int tax;

    @SerializedName("image_link")
    public ImageModel imageModel;
}
