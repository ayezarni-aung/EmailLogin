package com.azna.emaillogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.azna.emaillogin.ApiUtils.ApiUtil;
import com.azna.emaillogin.api.ApiInterface;
import com.azna.emaillogin.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        apiInterface = ApiUtil.getApi();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailaddr, pwd;
                emailaddr = email.getText().toString().trim();
                pwd = password.getText().toString().trim();
                if (!TextUtils.isEmpty(emailaddr) && !TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "TextUtil", Toast.LENGTH_LONG).show();
                    sendPost(emailaddr, pwd);
                    SharedPreferences mPref = getSharedPreferences("Pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("EMAIL", emailaddr);
                    editor.putString("PASSWORD", pwd);
                    editor.apply();
                }

            }
        });
    }

    private void sendPost(String emailaddr, String pwd) {
        apiInterface.saveLogin(emailaddr, pwd).enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                Toast.makeText(LoginActivity.this, "Successful ..", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed ..", Toast.LENGTH_LONG).show();

            }


        });
    }
}
