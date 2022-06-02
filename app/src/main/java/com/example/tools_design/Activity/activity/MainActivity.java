package com.example.tools_design.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tools_design.Activity.fragment.LoginFragment;
import com.example.tools_design.Activity.fragment.RegisterFragment;
import com.example.tools_design.R;
import com.example.tools_design.Utils.MethodUtils;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    private void initView() {
        replaceFragment(new LoginFragment());
    }

    private void initData() {
    }

    private void initListener() {
    }


    /**
     * 切换fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_frameLayout,fragment);
        fragmentTransaction.commit();
    }
}