package com.sohel.dokandar24admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sohel.dokandar24admin.Common.ApiCall.AdminApi;
import com.sohel.dokandar24admin.Common.LocalDb.UserDb;
import com.sohel.dokandar24admin.Common.Responses.RetrofitResponses2;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


     private EditText emailEt,passwordEt;
    private Button loginButton;
    private ProgressBar progressBar;
    private AdminApi adminApi;
    private UserDb userDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailEt.getText().toString().trim();
                String password=passwordEt.getText().toString().trim();
                if(email.isEmpty()){
                    showError(emailEt,"Please Enter Your Email.");
                }else if(password.isEmpty()){
                    showError(passwordEt,"Please Enter Password");
                }else{
                    loginSeller(email,password);
                }
            }
        });
    }
    private void loginSeller(String email, String password) {
        Map<String,Object> hashMap=new HashMap<>();
        hashMap.put("email",email);
        hashMap.put("password",password);

        adminApi.loginAdmin(hashMap, progressBar, new RetrofitResponses2() {
            @Override
            public void onSuccess(String message, ProgressBar progressDialog) {
                sendToMainActivity();
                progressDialog.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message, ProgressBar progressDialog) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });




    }
    @Override
    protected void onStart() {
        super.onStart();
        userDb=new UserDb(this);
        String token=userDb.getAccessToken();
        String userType=userDb.getUserType();

        if(!token.equals("") && userType.equals("admin")){
            sendToMainActivity();
        }
    }

    private void init() {
        userDb=new UserDb(this);
        adminApi=new AdminApi(this);
        progressBar=findViewById(R.id.progressBar);
        emailEt=findViewById(R.id.emailEt);
        passwordEt=findViewById(R.id.passwordEt);
        loginButton=findViewById(R.id.loginButtonEt);
    }
    private void showError(EditText editText, String message){
        editText.setError(message);
        editText.requestFocus();
    }

    private void sendToMainActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}