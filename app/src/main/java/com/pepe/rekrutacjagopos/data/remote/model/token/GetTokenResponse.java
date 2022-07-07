package com.pepe.rekrutacjagopos.data.remote.model.token;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTokenResponse {

    @SerializedName("access_token")
    public String token;
}
