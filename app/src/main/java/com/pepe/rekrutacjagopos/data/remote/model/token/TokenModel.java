package com.pepe.rekrutacjagopos.data.remote.model.token;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokenModel {

    @SerializedName("access_token")
    public String token;

    @SerializedName("token_type")
    public String type;
}
