package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

import com.sohel.dokandar24admin.Common.Model.ShopModel;

public interface RetrofitShopResponse {
    void onSuccess(String message, ProgressDialog progressDialog, ShopModel shop);
    void onError(String message, ProgressDialog progressDialog);
}
