package com.example.tools_design.Activity.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tools_design.Activity.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class ContainerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    /**
     * 使用fragmentActivity，传入context
     * 若fragmentList不存在，则初始化fragmentList
     * @param fragmentActivity
     */
    public ContainerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        if(fragmentList == null){
            fragmentList = new ArrayList<>();
        }
    }

    /**
     * 向fragmentList增加fragment
     * @param fragment
     */
    public void addFragment(Fragment fragment){
        if(fragmentList!=null){
            fragmentList.add(fragment);
        }
    }

    /**
     * 返回位置
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    /**
     * 返回尺寸
     * @return
     */
    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
