package com.pepe.rekrutacjagopos.data.remote.model.item;

import com.google.gson.annotations.SerializedName;

public class ImageModel {

    @SerializedName("default_link")
    public String defaultImage;

    @SerializedName("small")
    public String smallImage;
}
