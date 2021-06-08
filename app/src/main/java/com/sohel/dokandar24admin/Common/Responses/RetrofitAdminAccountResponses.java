package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

import com.sohel.dokandar24admin.Common.Model.AdminAccountModel;
import com.sohel.dokandar24admin.Common.Model.AppSettingModel;

public interface RetrofitAdminAccountResponses {
    void onSuccess(String message, ProgressDialog progressDialog, AdminAccountModel adminAccountModel);
    void onError(String message, ProgressDialog progressDialog);
}
