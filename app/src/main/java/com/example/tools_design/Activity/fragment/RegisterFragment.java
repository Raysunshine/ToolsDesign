package com.example.tools_design.Activity.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tools_design.R;


public class RegisterFragment extends Fragment {

    private View view;
    private TextView fragment_register_title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        initView();

        initData();

        initListener();

        return view;
    }

    private void initView() {
        fragment_register_title = view.findViewById(R.id.fragment_register_title);
    }

    private void initData() {
    }

    private void initListener() {
        fragment_register_title.setOnClickListener(v -> {
            replaceFragment(new LoginFragment());
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
        fragmentTransaction.replace(R.id.activity_main_frameLayout,fragment).commit();
    }
}