package com.sohel.dokandar24admin.Common.Interfaces;

import android.app.ProgressDialog;
import android.widget.ProgressBar;

import com.sohel.dokandar24admin.Common.Responses.RetrofitAccountHistoryListResponse;
import com.sohel.dokandar24admin.Common.Responses.RetrofitAdminAccountResponses;
import com.sohel.dokandar24admin.Common.Responses.RetrofitCashInListResponse;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses2;
import com.sohel.dokandar24admin.Common.Responses.RetrofitSettingResponses;

import java.util.Map;

public interface UserInterface {

    void getCashIn(String token, String cashInType, ProgressDialog progressDialog, RetrofitCashInListResponse retrofitResponse);
    void loginAdmin(Map<String,Object> sellerMap, ProgressBar progressDialog, RetrofitResponses2 retrofitResponses);
    void getAppSetting(String token, ProgressDialog progressDialog, RetrofitSettingResponses retrofitResponses);
    void getAdminAccountBalance(String token, ProgressDialog progressDialog, RetrofitAdminAccountResponses retrofitResponses);
    void getAccountHistory(String token, ProgressDialog progressDialog, RetrofitAccountHistoryListResponse retrofitResponses);
    void setAppSetting(String token,Map<String,Object> settingMap, ProgressDialog progressDialog, RetrofitResponses retrofitResponses);
    void setAdminAccountBalance(String token,Map<String,Object> accountMap, ProgressDialog progressDialog, RetrofitResponses retrofitResponses);
    void paidBalance(String token,String id, ProgressDialog progressDialog, RetrofitResponses retrofitResponses);

}
