package com.example.tools_design.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tools_design.Activity.adapter.ContainerAdapter;
import com.example.tools_design.Activity.fragment.HomeFragment;
import com.example.tools_design.Activity.fragment.LoginFragment;
import com.example.tools_design.Activity.fragment.MineFragment;
import com.example.tools_design.Activity.fragment.SpecialFragment;
import com.example.tools_design.R;
import com.example.tools_design.Utils.Constant;

public class ContainerActivity extends AppCompatActivity{

    private ViewPager2 activity_container_viewPager2;
    private ContainerAdapter containerAdapter;
    private RadioGroup activity_container_radioGroup;
    private RadioButton activity_container_radioButton_home;
    private RadioButton activity_container_radioButton_special;
    private RadioButton activity_container_radioButton_mine;
    private HomeFragment homeFragment;
    private SpecialFragment specialFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        initView();
        
        initData();
        
        initListener();
    }

    private void initView() {
        activity_container_viewPager2 = findViewById(R.id.activity_container_viewPager2);
        activity_container_radioGroup = findViewById(R.id.activity_container_radioGroup);
        activity_container_radioButton_home = findViewById(R.id.activity_container_radioButton_home);
        activity_container_radioButton_special = findViewById(R.id.activity_container_radioButton_special);
        activity_container_radioButton_mine = findViewById(R.id.activity_container_radioButton_mine);
    }

    private void initData() {
        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        mineFragment = new MineFragment();
        containerAdapter = new ContainerAdapter(this);
        activity_container_viewPager2.setAdapter(containerAdapter);
        containerAdapter.addFragment(homeFragment);
        containerAdapter.addFragment(specialFragment);
        containerAdapter.addFragment(mineFragment);

        int count = activity_container_radioGroup.getChildCount();
        Log.d(Constant.TAG, "initData: "+count);

        activity_container_viewPager2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                switch (v.getId()){
                    case R.layout.fragment_home:
                        activity_container_radioButton_home.setChecked(true);
                        break;
                    case R.layout.fragment_special:
                        activity_container_radioButton_special.setChecked(true);
                        break;
                    case R.layout.fragment_mine:
                        activity_container_radioButton_mine.setChecked(true);
                        break;
                }
            }
        });

    }

    private void initListener() {
        activity_container_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.activity_container_radioButton_home:
                        activity_container_viewPager2.setCurrentItem(0);
                        break;
                    case R.id.activity_container_radioButton_special:
                        activity_container_viewPager2.setCurrentItem(1);
                        break;
                    case R.id.activity_container_radioButton_mine:
                        //TODO 实现删除第二个fragment
                        activity_container_radioButton_special.setVisibility(View.GONE);
                        containerAdapter.notifyItemRemoved(1);
                        containerAdapter.deleteFragment(specialFragment);
                        activity_container_viewPager2.setCurrentItem(2);
                        break;
                }
            }
        });
    }


}