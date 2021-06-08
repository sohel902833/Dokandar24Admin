package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

import com.sohel.dokandar24admin.Common.Model.CashInModel;

import java.util.List;

public interface RetrofitCashInListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, List<CashInModel> cashInList);
    void onError(String message, ProgressDialog progressDialog);
}
