package com.enjin.enjincoin.sdk.model.service.identities;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CreateIdentityResult {

    @SerializedName("CreateEnjinIdentity")
    private Identity identity;

}