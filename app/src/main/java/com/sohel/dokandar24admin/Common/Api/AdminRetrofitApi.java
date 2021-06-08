package com.sohel.dokandar24admin.Common.Api;



import com.sohel.dokandar24admin.Common.Model.AccountHistoryModelList;
import com.sohel.dokandar24admin.Common.Model.AdminAccountModel;
import com.sohel.dokandar24admin.Common.Model.AppSettingModel;
import com.sohel.dokandar24admin.Common.Model.CashInListModel;
import com.sohel.dokandar24admin.Common.Model.SellerListModel;
import com.sohel.dokandar24admin.Common.Model.SellerModel;
import com.sohel.dokandar24admin.Common.Model.SendMoneyListModel;
import com.sohel.dokandar24admin.Common.Model.ShopModel;
import com.sohel.dokandar24admin.Common.Responses.CustomResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AdminRetrofitApi {
   @GET("admin/cashin/{type}")
    Call<CashInListModel> getCashIn(@Header("Authorization") String token, @Path("type") String cashInType);

    @POST("admin/login")
    Call<CustomResponse> loginAdmin(@Body Map<String,Object> seller);

    @POST("admin/cashin/{id}")
    Call<CustomResponse> paidCashIn(@Header("Authorization") String token,@Path("id") String id);

    @GET("setting")
    Call<AppSettingModel> getAppSetting(@Header("Authorization") String token);
    @GET("admin/account")
    Call<AdminAccountModel> getAdminAccount(@Header("Authorization") String token);

     @GET("admin/account/history")
     Call<AccountHistoryModelList> getAdminAccountHistory(@Header("Authorization") String token);
    @POST("admin/account/add")
    Call<CustomResponse> setAdminAccountBalance(@Header("Authorization") String token,@Body Map<String,Object> accountMap);


    @POST("setting")
    Call<CustomResponse> setAppSetting(@Header("Authorization") String token,@Body Map<String,Object> settingMap);

}
