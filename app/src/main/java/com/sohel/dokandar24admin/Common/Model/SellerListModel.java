package com.sohel.dokandar24admin.Common.Model;

import java.util.List;

public class SellerListModel {
    List<SellerModel> seller;

    public SellerListModel(){

    }

    public SellerListModel(List<SellerModel> seller) {
        this.seller = seller;
    }

    public List<SellerModel> getSeller() {
        return seller;
    }


}
