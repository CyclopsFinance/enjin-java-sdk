package com.enjin.enjincoin.sdk.service.requests.vo.data;

import com.enjin.enjincoin.sdk.service.requests.vo.Transaction;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestsData {

    @SerializedName("EnjinTransactions")
    private List<Transaction> requests;

    public List<Transaction> getRequests() {
        return this.requests;
    }

    public boolean isEmpty() {
        return this.requests == null || this.requests.isEmpty();
    }
}