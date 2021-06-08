package com.sohel.dokandar24admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohel.dokandar24admin.Common.Model.AdminAccountHistoryModel;
import com.sohel.dokandar24admin.R;

import java.util.ArrayList;
import java.util.List;

public class AccountHistoryAdapter extends RecyclerView.Adapter<AccountHistoryAdapter.MyViewHolder> {
   private Context context;
   private List<AdminAccountHistoryModel> accountHistoryList=new ArrayList<>();
    public AccountHistoryAdapter(Context context, List<AdminAccountHistoryModel> accountHistoryList) {
        this.context = context;
        this.accountHistoryList = accountHistoryList;
     }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.account_history_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdminAccountHistoryModel currentItem=accountHistoryList.get(position);
        holder.timeTv.setText("Time: "+currentItem.getTime());
        holder.textTv.setText(""+currentItem.getText());
        holder.amounTv.setText(currentItem.getAmount()+"tk");

    }

    @Override
    public int getItemCount() {
        return accountHistoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textTv,timeTv,amounTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           timeTv=itemView.findViewById(R.id.h_TimeTv);
           amounTv=itemView.findViewById(R.id.h_amountTv);
           textTv=itemView.findViewById(R.id.h_TextTv);
        }
    }
}
