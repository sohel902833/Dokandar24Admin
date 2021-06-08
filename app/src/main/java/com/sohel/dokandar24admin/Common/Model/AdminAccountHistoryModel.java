package com.sohel.dokandar24admin.Common.Model;

public class AdminAccountHistoryModel {

    int id;
    String time,amount,text;

    public AdminAccountHistoryModel(){

    }

    public AdminAccountHistoryModel(int id, String time, String amount, String text) {
        this.id = id;
        this.time = time;
        this.amount = amount;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
