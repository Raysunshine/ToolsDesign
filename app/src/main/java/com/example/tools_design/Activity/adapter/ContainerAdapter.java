package com.example.tools_design.Activity.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContainerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    /**
     * 使用fragmentActivity，传入context
     * 若fragmentList不存在，则初始化fragmentList
     *
     */
    public ContainerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
    }

    /**
     * 向fragmentList增加fragment
     *
     */
    public void addFragment(Fragment fragment) {
        if (fragmentList != null) {
            fragmentList.add(fragment);
        }
    }

    /**
     * 删除某个fragment
     *
     */
    public void deleteFragment(Fragment fragment) {
        fragmentList.remove(fragment);
    }

    /**
     * 返回位置
     *
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    /**
     * 返回尺寸
     *
     */
    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
