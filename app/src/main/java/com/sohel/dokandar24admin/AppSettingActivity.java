package com.sohel.dokandar24admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sohel.dokandar24admin.Common.ApiCall.AdminApi;
import com.sohel.dokandar24admin.Common.Common;
import com.sohel.dokandar24admin.Common.LocalDb.UserDb;
import com.sohel.dokandar24admin.Common.Model.AppSettingModel;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses;
import com.sohel.dokandar24admin.Common.Responses.RetrofitSettingResponses;

import java.util.HashMap;
import java.util.Map;

public class AppSettingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText versionEt,priorityEt,cashoutEt,convertBalanceEt,sendBalanceEt,referBonusEt;
    private Button updateSettingButton;
    private ProgressDialog progressDialog;
    private UserDb userDb;
    private AdminApi adminApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);
        init();

        updateSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cashOut=cashoutEt.getText().toString();
                String sendMoney=sendBalanceEt.getText().toString();
                String convertBalance=convertBalanceEt.getText().toString();
                String referBonus=referBonusEt.getText().toString();
                String versionNo=versionEt.getText().toString();
                String priority=priorityEt.getText().toString();

                if(versionNo.isEmpty()){
                    versionEt.setError("Enter Application Version");
                    versionEt.requestFocus();
                }else if(priority.isEmpty()){
                    priorityEt.setError("Enter Priority");
                    priorityEt.requestFocus();
                }else if(cashOut.isEmpty()){
                    Common.showEditTextError(cashoutEt,"Enter cashOut Charge.");
                }else if(sendMoney.isEmpty()){
                    Common.showEditTextError(sendBalanceEt,"Enter Send Money Charge.");
                }else if(convertBalance.isEmpty()){
                    Common.showEditTextError(convertBalanceEt,"Enter Convert Balance Charge.");
                }else if(referBonus.isEmpty()){
                    Common.showEditTextError(referBonusEt,"Enter Refer Bonus.");
                }else{
                    Map<String,Object> hashMap=new HashMap<>();
                    hashMap.put("cashOut", Integer.parseInt(cashOut));
                    hashMap.put("sendMoney",Integer.parseInt(sendMoney));
                    hashMap.put("convertBalance",Integer.parseInt(convertBalance));
                    hashMap.put("referBonus",Integer.parseInt(referBonus));
                    hashMap.put("versionNo", Double.parseDouble(versionNo));
                    hashMap.put("priority",Integer.parseInt(priority));
                    updateAppSettings(hashMap);
                }
            }
        });





    }
    private void init() {
        toolbar=findViewById(R.id.appBariId);
        setSupportActionBar(toolbar);
        this.setTitle("Application Setting");

        progressDialog=new ProgressDialog(this);
        versionEt=findViewById(R.id.versionEt);
        priorityEt=findViewById(R.id.priorityEt);
        cashoutEt=findViewById(R.id.cashoutEt);
        convertBalanceEt=findViewById(R.id.convertCashEt);
        sendBalanceEt=findViewById(R.id.sendBalanceEt);
        referBonusEt=findViewById(R.id.referBonusEt);
        updateSettingButton=findViewById(R.id.updateSettingBtn);

        userDb=new UserDb(this);
        adminApi=new AdminApi(this);



    }
    @Override
    protected void onStart() {
        super.onStart();
        loadAppSetting();
    }
    private void loadAppSetting() {
        progressDialog.setMessage("Loading..");
        String token=userDb.getAccessToken();
        adminApi.getAppSetting(token, progressDialog, new RetrofitSettingResponses() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, AppSettingModel appSetting) {
                    progressDialog.dismiss();
                    versionEt.setText(""+appSetting.getVersionNo());
                    priorityEt.setText(""+appSetting.getPriority());
                    cashoutEt.setText(""+appSetting.getCashOut());
                    sendBalanceEt.setText(""+appSetting.getSendMoney());
                    convertBalanceEt.setText(""+appSetting.getConvertBalance());
                    referBonusEt.setText(""+appSetting.getReferBonus());
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(AppSettingActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateAppSettings(Map<String,Object> hashMap) {
        progressDialog.setMessage("Saving..");
        String token=userDb.getAccessToken();
        adminApi.setAppSetting(token, hashMap, progressDialog, new RetrofitResponses() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog) {
                onStart();
                progressDialog.dismiss();
                Toast.makeText(AppSettingActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                 progressDialog.dismiss();
                Toast.makeText(AppSettingActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}