package com.azna.emaillogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.azna.emaillogin.ApiUtils.ApiUtil;
import com.azna.emaillogin.api.ApiInterface;
import com.azna.emaillogin.model.UserResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout email, password;
    Button login;
    private ApiInterface apiInterface;
    ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        apiInterface = ApiUtil.getApi();
        simpleProgressBar=findViewById(R.id.simpleProgressBar); // initiate the progress bar
        simpleProgressBar.setMax(20);
        simpleProgressBar.getProgressDrawable();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleProgressBar.setVisibility(View.VISIBLE);
                String emailaddr, pwd;
                emailaddr = email.getEditText().getText().toString().trim();
                pwd = password.getEditText().getText().toString().trim();
                if (!TextUtils.isEmpty(emailaddr) && !TextUtils.isEmpty(pwd)) {
                    sendPost(emailaddr, pwd);

                }

            }
        });
    }

    private void sendPost(final String email, final String pwd) {
        apiInterface.saveLogin(email, pwd).enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userRespon=response.body();
                Boolean error=userRespon.isError();
                if (error){
                    simpleProgressBar.setVisibility(View.GONE);

                    Toast.makeText(LoginActivity.this,userRespon.getMessage(), Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences mPref = getSharedPreferences("Pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("EMAIL", email);
                    editor.putString("PASSWORD", pwd);
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Successful ..", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Failed ..", Toast.LENGTH_LONG).show();

            }


        });
    }
}
