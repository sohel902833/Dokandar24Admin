package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

import com.sohel.dokandar24admin.Common.Model.SellerModel;

public interface RetrofitSellerResponses {
    void onSuccess(String message, ProgressDialog progressDialog, SellerModel seller);
    void onError(String message, ProgressDialog progressDialog);
}
