package com.sohel.dokandar24admin.Common.Model;

import java.util.List;

public class AccountHistoryModelList {
    List<AdminAccountHistoryModel> accountHistory;

    public AccountHistoryModelList(){}
    public AccountHistoryModelList(List<AdminAccountHistoryModel> accountHistory) {
        this.accountHistory = accountHistory;
    }

    public List<AdminAccountHistoryModel> getAccountHistory() {
        return accountHistory;
    }
}
