package com.azna.emaillogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_page);
        SharedPreferences mPref=getSharedPreferences("Pref",MODE_PRIVATE);
      String email=  mPref.getString("EMAIL","");
       String pwd= mPref.getString("PASSWORD","");
        SharedPreferences.Editor editor=mPref.edit();
        editor.apply();
        Intent intent;
        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd)){
             intent=new Intent(MainPage.this,LoginActivity.class);
        }

        else {
             intent = new Intent(MainPage.this, WelcomeActivity.class);
        }
        startActivity(intent);
        finish();

    }
}
