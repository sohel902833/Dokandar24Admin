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
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses;
import com.sohel.dokandar24admin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class PendingCashInFragment extends Fragment {
    public PendingCashInFragment() {
    }
    private RecyclerView recyclerView;
    private CashInAdapter cashInAdapter;
    AdminApi adminApi;
    UserDb userDb;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_pending_cash_in, container, false);

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
        adminApi.getCashIn(token, "pending", progressDialog, new RetrofitCashInListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, List<CashInModel> dcashInList) {
                cashInAdapter=new CashInAdapter(getContext(),dcashInList);
                recyclerView.setAdapter(cashInAdapter);
                progressDialog.dismiss();

                cashInAdapter.setOnItemClickListner(new CashInAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(int id) {
                        String token=userDb.getAccessToken();
                        progressDialog.setMessage("Please Wait..");
                        adminApi.paidBalance(token, String.valueOf(id), progressDialog, new RetrofitResponses() {
                            @Override
                            public void onSuccess(String message, ProgressDialog progressDialog) {
                                onStart();
                                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onError(String message, ProgressDialog progressDialog) {
                                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                });




            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });




    }

}