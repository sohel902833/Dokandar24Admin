package com.sohel.dokandar24admin.Common.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sohel.dokandar24admin.BaseUrl;
import com.sohel.dokandar24admin.Common.Api.AdminRetrofitApi;
import com.sohel.dokandar24admin.Common.Interfaces.UserInterface;
import com.sohel.dokandar24admin.Common.LocalDb.UserDb;
import com.sohel.dokandar24admin.Common.Model.AccountHistoryModelList;
import com.sohel.dokandar24admin.Common.Model.AdminAccountModel;
import com.sohel.dokandar24admin.Common.Model.AppSettingModel;
import com.sohel.dokandar24admin.Common.Model.CashInListModel;
import com.sohel.dokandar24admin.Common.Responses.CustomResponse;
import com.sohel.dokandar24admin.Common.Responses.RetrofitAccountHistoryListResponse;
import com.sohel.dokandar24admin.Common.Responses.RetrofitAdminAccountResponses;
import com.sohel.dokandar24admin.Common.Responses.RetrofitCashInListResponse;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses2;
import com.sohel.dokandar24admin.Common.Responses.RetrofitSettingResponses;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminApi implements UserInterface {

    AdminRetrofitApi userRetrofitApi;
    UserDb userDb;
    private Activity context;
    public AdminApi(Activity context) {
        this.context = context;

        userDb = new UserDb(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRetrofitApi = retrofit.create(AdminRetrofitApi.class);

    }
    @Override
    public void getCashIn(String token, String cashInType, ProgressDialog progressDialog, RetrofitCashInListResponse retrofitResponse) {
        progressDialog.show();
        Call<CashInListModel> call=userRetrofitApi.getCashIn(token,cashInType);
        call.enqueue(new Callback<CashInListModel>() {
            @Override
            public void onResponse(Call<CashInListModel> call, Response<CashInListModel> response) {

                CashInListModel cashInList=response.body();
                if(!response.isSuccessful()){
                    retrofitResponse.onError("Error..",progressDialog);
                }else{
                    try{
                        if(cashInList.getCashIn()!=null && cashInList.getCashIn().size()>0){
                            retrofitResponse.onSuccess("Success",progressDialog,cashInList.getCashIn());
                         }else{
                            retrofitResponse.onError("No Cash In Found",progressDialog);
                        }
                    }catch (Exception e){
                        Toast.makeText(context, ""+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CashInListModel> call, Throwable t) {
                retrofitResponse.onError("Error..",progressDialog);
            }
        });
    }
    @Override
    public void loginAdmin(Map<String, Object> adminMap, ProgressBar progressDialog, RetrofitResponses2 retrofitResponses) {
        progressDialog.setVisibility(View.VISIBLE);
        Call<CustomResponse> call=userRetrofitApi.loginAdmin(adminMap);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                CustomResponse customResponse=response.body();
                if(!response.isSuccessful()){
                    String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }else{
                    try{
                        userDb.setUserData("Bearer "+customResponse.getToken(),"admin");
                        String message=customResponse==null?"Login Successful.":customResponse.getMessage();
                        retrofitResponses.onSuccess(message,progressDialog);
                    }catch (Exception e){
                        Toast.makeText(context, ""+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                retrofitResponses.onError("Login Failed",progressDialog);

            }
        });
    }

    @Override
    public void getAppSetting(String token, ProgressDialog progressDialog, RetrofitSettingResponses retrofitResponses) {
        progressDialog.show();
        Call<AppSettingModel> call=userRetrofitApi.getAppSetting(token);
        call.enqueue(new Callback<AppSettingModel>() {
            @Override
            public void onResponse(Call<AppSettingModel> call, Response<AppSettingModel> response) {
                    AppSettingModel setting=response.body();
                    if(!response.isSuccessful()){
                        retrofitResponses.onError("Setting Get Failed.",progressDialog);
                    }else{
                        if(setting!=null){
                            retrofitResponses.onSuccess("Success",progressDialog,setting);
                        }else{
                            retrofitResponses.onError("Setting Not Found",progressDialog);
                        }
                    }
            }

            @Override
            public void onFailure(Call<AppSettingModel> call, Throwable t) {
                retrofitResponses.onError("Setting Get Failed.",progressDialog);
            }
        });
    }

    @Override
    public void getAdminAccountBalance(String token, ProgressDialog progressDialog, RetrofitAdminAccountResponses retrofitResponses) {
        progressDialog.show();
        Call<AdminAccountModel> call=userRetrofitApi.getAdminAccount(token);
        call.enqueue(new Callback<AdminAccountModel>() {
            @Override
            public void onResponse(Call<AdminAccountModel> call, Response<AdminAccountModel> response) {
                AdminAccountModel accountModel=response.body();
                if(!response.isSuccessful()){
                    retrofitResponses.onError("Account Balance Not Found",progressDialog);
                }else{
                    if(accountModel!=null){
                        retrofitResponses.onSuccess("Success",progressDialog,accountModel);
                    }else{
                        retrofitResponses.onError("Account Balance Not Found",progressDialog);
                    }
                }
            }

            @Override
            public void onFailure(Call<AdminAccountModel> call, Throwable t) {
                retrofitResponses.onError("Account Balance Not Found",progressDialog);
            }
        });
    }

    @Override
    public void getAccountHistory(String token, ProgressDialog progressDialog, RetrofitAccountHistoryListResponse retrofitResponses) {
        progressDialog.show();
        Call<AccountHistoryModelList> call=userRetrofitApi.getAdminAccountHistory(token);
        call.enqueue(new Callback<AccountHistoryModelList>() {
            @Override
            public void onResponse(Call<AccountHistoryModelList> call, Response<AccountHistoryModelList> response) {
               AccountHistoryModelList accountHistoryModelList=response.body();
                if(!response.isSuccessful()){
                   retrofitResponses.onError("Error.",progressDialog);
                }else{
                    if(accountHistoryModelList.getAccountHistory()!=null && accountHistoryModelList.getAccountHistory().size()>0){
                        retrofitResponses.onSuccess("Success",progressDialog,accountHistoryModelList.getAccountHistory());
                    }else{
                        retrofitResponses.onError("No History Found.",progressDialog);
                    }
                 }
            }

            @Override
            public void onFailure(Call<AccountHistoryModelList> call, Throwable t) {
                retrofitResponses.onError("Error.",progressDialog);
            }
        });


    }

    @Override
    public void setAppSetting(String token, Map<String, Object> settingMap, ProgressDialog progressDialog, RetrofitResponses retrofitResponses) {
        progressDialog.show();
        Call<CustomResponse> call=userRetrofitApi.setAppSetting(token,settingMap);

        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                CustomResponse customResponse=response.body();
                if(!response.isSuccessful()){
                    String message=customResponse==null?"Setting Set Failed":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }else{
                      String message=customResponse==null?"Setting Set Successful":customResponse.getMessage();
                        retrofitResponses.onSuccess(message,progressDialog);
                    }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                retrofitResponses.onError("Setting Set Failed.",progressDialog);
            }
        });


    }

    @Override
    public void setAdminAccountBalance(String token, Map<String, Object> accountMap, ProgressDialog progressDialog, RetrofitResponses retrofitResponses) {
        progressDialog.show();
        Call<CustomResponse> call=userRetrofitApi.setAdminAccountBalance(token,accountMap);

        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                CustomResponse customResponse=response.body();
                if(!response.isSuccessful()){
                    String message=customResponse==null?"Cash In Failed":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }else{
                    String message=customResponse==null?"Cash In Successful":customResponse.getMessage();
                    retrofitResponses.onSuccess(message,progressDialog);
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                retrofitResponses.onError("Cash In Failed",progressDialog);
            }
        });
    }

    @Override
    public void paidBalance(String token, String id, ProgressDialog progressDialog, RetrofitResponses retrofitResponses) {
        progressDialog.show();

        Call<CustomResponse> call=userRetrofitApi.paidCashIn(token,id);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                CustomResponse customResponse=response.body();
                if(!response.isSuccessful()){
                    String message=customResponse==null?"Cash Paid Failed":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }else{
                    String message=customResponse==null?"Paid Successful":customResponse.getMessage();
                    retrofitResponses.onSuccess(message,progressDialog);
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                retrofitResponses.onError("Cash Paid Failed",progressDialog);
            }
        });



    }
}
