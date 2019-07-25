package com.azna.emaillogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartingPage extends AppCompatActivity {
    Button login_btn,sign_up_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        login_btn=findViewById(R.id.logIn);
        sign_up_btn=findViewById(R.id.signUP);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartingPage.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartingPage.this,PostActivity.class);
                startActivity(intent);

            }
        });


    }
}
