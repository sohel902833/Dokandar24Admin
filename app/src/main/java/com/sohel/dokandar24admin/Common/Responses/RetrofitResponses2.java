package com.sohel.dokandar24admin.Common.Responses;

import android.widget.ProgressBar;

public interface RetrofitResponses2 {
        void onSuccess(String message, ProgressBar progressDialog);
        void onError(String message, ProgressBar progressDialog);
}
