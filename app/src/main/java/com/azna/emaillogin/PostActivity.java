package com.azna.emaillogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.azna.emaillogin.ApiUtils.ApiUtil;
import com.azna.emaillogin.api.ApiInterface;
import com.azna.emaillogin.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    EditText username, email, password, confirm;
    Button signUp;
    private ApiInterface apiInterface;
    String user_name, emailaddr, pwd, confirm_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm_password);
        signUp = findViewById(R.id.sign_up);

        apiInterface = ApiUtil.getApi();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_name = username.getText().toString().trim();
                emailaddr = email.getText().toString().trim();
                pwd = password.getText().toString().trim();
                confirm_password = confirm.getText().toString();
                if(emailaddr.contains("@gmail.com") && !emailaddr.startsWith("0123456789") ) {
                    if (!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(emailaddr) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(confirm_password)) {
                        sendPost(user_name, emailaddr, pwd, confirm_password);
                    } else
                        Toast.makeText(PostActivity.this, " Enter All", Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(PostActivity.this,"Please Check Your Email...",Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private void sendPost(String user_name, String emailaddr, String pwd,String confirm_password) {
        apiInterface.savePost(user_name,emailaddr,pwd,confirm_password).
                enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()){
                    Toast.makeText(PostActivity.this,"Successful ..",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(PostActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(PostActivity.this,"unsuccess ..",Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(PostActivity.this,"Failed .."+call.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }
    }

