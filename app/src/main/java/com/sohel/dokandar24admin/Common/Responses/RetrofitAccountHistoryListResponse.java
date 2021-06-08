package com.sohel.dokandar24admin.Common.Responses;

import android.app.ProgressDialog;

import com.sohel.dokandar24admin.Common.Model.AdminAccountHistoryModel;
import com.sohel.dokandar24admin.Common.Model.SendMoneyModel;

import java.util.List;

public interface RetrofitAccountHistoryListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, List<AdminAccountHistoryModel> sendMoneyList);
    void onError(String message, ProgressDialog progressDialog);
}
