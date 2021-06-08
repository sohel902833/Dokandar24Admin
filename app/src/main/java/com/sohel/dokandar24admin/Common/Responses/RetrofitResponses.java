package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

public interface RetrofitResponses {
    void onSuccess(String message, ProgressDialog progressDialog);
    void onError(String message, ProgressDialog progressDialog);
}
