package com.example.tools_design.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;

import com.example.tools_design.Model.Model;
import com.example.tools_design.Model.bean.UserInfo;
import com.example.tools_design.R;
import com.example.tools_design.Utils.Constant;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initResponding();
    }

    private void initResponding() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String currentUser = Model.getInstance().getUserDao().getCurrentUser();
                if (TextUtils.isEmpty(currentUser)) {
                    //用户未登录
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    //用户已经登录
                    Intent intent = new Intent(SplashActivity.this, ContainerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}