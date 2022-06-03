package com.example.tools_design.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
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
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                //TODO 判断当前账号是否已经登录，若未登录，跳转到MainActivity界面，若登录，跳转到ContainerActivity
//                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        UserInfo userInfo = Model.getInstance().getUserDao().getUserInformationByUserName("123");
//                        Log.d(Constant.TAG, "run: "+userInfo.getIsOnline());
//                        Log.d(Constant.TAG, "run: "+userInfo.getUserName());
//                        Log.d(Constant.TAG, "run: "+userInfo.getPassword());
//                        Log.d(Constant.TAG, "run: "+userInfo.getNickName());
//                        if(userInfo.getIsOnline() == 0){
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    finish();
//                                }
//                            });
//                        }else{
//                            Log.d(Constant.TAG, "run: 登录失败");
//                        }
//                    }
//                });

            }
        });
    }
}