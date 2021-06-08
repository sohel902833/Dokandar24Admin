package com.sohel.dokandar24admin.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sohel.dokandar24admin.Common.ApiCall.AdminApi;
import com.sohel.dokandar24admin.Common.LocalDb.UserDb;
import com.sohel.dokandar24admin.Common.Model.AdminAccountModel;
import com.sohel.dokandar24admin.Common.Responses.RetrofitAdminAccountResponses;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses;
import com.sohel.dokandar24admin.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AdminProfileFragment extends Fragment {

    public AdminProfileFragment() {
   }
    private Button cashInButton;
    private EditText amountEt;
    private TextView amountTv;

    AdminApi adminApi;
    UserDb userDb;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin_profile, container, false);



        adminApi=new AdminApi(getActivity());
        userDb=new UserDb(getActivity());
        progressDialog=new ProgressDialog(getContext());

        cashInButton=view.findViewById(R.id.cashin_Button);
        amountEt=view.findViewById(R.id.amountEt);
        amountTv=view.findViewById(R.id.amountTv);


        cashInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=amountEt.getText().toString();
                if(amount.isEmpty()){
                    amountEt.setError("Enter Some Amount..");
                    amountEt.requestFocus();
                    return;
                }else{
                    int am=Integer.parseInt(amount);
                    cashIn(am);
                }
            }
        });





        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading..");
        String token=userDb.getAccessToken();
        adminApi.getAdminAccountBalance(token, progressDialog, new RetrofitAdminAccountResponses() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, AdminAccountModel adminAccountModel) {
                  progressDialog.dismiss();
                  amountTv.setText("Balance : "+adminAccountModel.getAccountBalance()+"tk");
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void cashIn(int amount){

        progressDialog.setMessage("Please Wait..");
        Calendar calForDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MMMM-yyy");
        String saveCurrentDate=currentDate.format(calForDate.getTime());

        Calendar callForTime=Calendar.getInstance();
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm");
        String saveCurrentTime=currentTime.format(callForTime.getTime());
        String token=userDb.getAccessToken();
        String totalTime=saveCurrentDate+" at "+saveCurrentTime;


        Map<String,Object> cashInMap=new HashMap<>();
        cashInMap.put("amount",amount);
        cashInMap.put("currentTime",totalTime);

        adminApi.setAdminAccountBalance(token, cashInMap, progressDialog, new RetrofitResponses() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog) {
                onStart();
                progressDialog.dismiss();
                amountEt.setText("");
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });



    }



}