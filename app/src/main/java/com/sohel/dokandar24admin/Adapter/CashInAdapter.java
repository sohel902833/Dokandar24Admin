package com.sohel.dokandar24admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sohel.dokandar24admin.Common.Model.CashInModel;
import com.sohel.dokandar24admin.R;

import java.util.ArrayList;
import java.util.List;

public class CashInAdapter extends RecyclerView.Adapter<CashInAdapter.MyViewHolder> {
   private Context context;
   private List<CashInModel> cashInList=new ArrayList<>();
    private  OnItemClickListner listner;

    public CashInAdapter(Context context, List<CashInModel> cashInList) {
        this.context = context;
        this.cashInList = cashInList;
     }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cash_in_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CashInModel currentItem=cashInList.get(position);
        holder.nameTv.setText(currentItem.getSellerName());
        holder.timeTv.setText(currentItem.getTime());
        holder.amounTv.setText(currentItem.getAmount()+"tk");
        holder.phoneTv.setText(currentItem.getSellerPhone());
        String state=currentItem.getState();
        if(state.equals("paid")){
            holder.paidButton.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(R.drawable.paid);
        }else if(state.equals("pending")){
            holder.paidButton.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
        }

        holder.paidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner!=null){
                    int id=currentItem.getId();
                    if(position!= RecyclerView.NO_POSITION){
                        listner.onItemClick(id);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cashInList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTv,timeTv,phoneTv,amounTv;
        private ImageView imageView;
        private Button paidButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.c_NameTv);
            timeTv=itemView.findViewById(R.id.c_timeTv);
            phoneTv=itemView.findViewById(R.id.c_PhoneTv);
            imageView=itemView.findViewById(R.id.c_ImageView);
            amounTv=itemView.findViewById(R.id.c_amountTv);
            paidButton=itemView.findViewById(R.id.paidButton);
        }
    }
    public interface  OnItemClickListner{
        void onItemClick(int id);
    }


    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }

}
