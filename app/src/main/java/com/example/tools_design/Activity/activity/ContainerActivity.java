package com.example.tools_design.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.tools_design.Activity.adapter.ContainerAdapter;
import com.example.tools_design.Activity.fragment.HomeFragment;
import com.example.tools_design.Activity.fragment.MineFragment;
import com.example.tools_design.R;

public class ContainerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        initView();
        
        initData();
        
        initListener();
    }

    private void initView() {
        ViewPager2 activity_container_viewPager2 = findViewById(R.id.activity_container_viewPager2);
        ContainerAdapter containerAdapter = new ContainerAdapter(this);
        activity_container_viewPager2.setAdapter(containerAdapter);
        containerAdapter.addFragment(new MineFragment());
        containerAdapter.addFragment(new HomeFragment());
        //默认为0
//        activity_container_viewPager2.setCurrentItem(0);
    }

    private void initData() {
    }

    private void initListener() {
    }
}