package com.sohel.dokandar24admin.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sohel.dokandar24admin.Adapter.CashInAdapter;
import com.sohel.dokandar24admin.Common.ApiCall.AdminApi;
import com.sohel.dokandar24admin.Common.LocalDb.UserDb;
import com.sohel.dokandar24admin.Common.Model.CashInModel;
import com.sohel.dokandar24admin.Common.Responses.RetrofitCashInListResponse;
import com.sohel.dokandar24admin.R;

import java.util.List;

public class PaidCashInFragment extends Fragment {
    public PaidCashInFragment() {
    }

    private RecyclerView recyclerView;
    private CashInAdapter cashInAdapter;
    AdminApi adminApi;
    UserDb userDb;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_paid_cash_in, container, false);

        adminApi=new AdminApi(getActivity());
        userDb=new UserDb(getActivity());
        progressDialog=new ProgressDialog(getContext());
        recyclerView=view.findViewById(R.id.cashInRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return  view;
    }
    @Override
    public void onStart() {
        super.onStart();
        String token=userDb.getAccessToken();
        progressDialog.setMessage("Loading..");
        adminApi.getCashIn(token, "paid", progressDialog, new RetrofitCashInListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, List<CashInModel> dcashInList) {
                cashInAdapter=new CashInAdapter(getContext(),dcashInList);
                recyclerView.setAdapter(cashInAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}