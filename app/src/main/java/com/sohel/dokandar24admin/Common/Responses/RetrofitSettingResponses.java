package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

import com.sohel.dokandar24admin.Common.Model.AppSettingModel;
import com.sohel.dokandar24admin.Common.Model.SellerModel;

public interface RetrofitSettingResponses {
    void onSuccess(String message, ProgressDialog progressDialog, AppSettingModel setting);
    void onError(String message, ProgressDialog progressDialog);
}
