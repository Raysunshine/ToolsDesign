package com.example.tools_design.Activity.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tools_design.Activity.activity.ContainerActivity;
import com.example.tools_design.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText fragment_login_userName;
    private EditText fragment_login_password;
    private TextView fragment_login_register;
    private Button fragment_login_login;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        initView();

        initData();

        initListener();

        return view;
    }

    private void initView() {
        fragment_login_userName = view.findViewById(R.id.fragment_login_userName);
        fragment_login_password = view.findViewById(R.id.fragment_login_password);
        fragment_login_register = view.findViewById(R.id.fragment_login_register);
        fragment_login_login = view.findViewById(R.id.fragment_login_login);
    }

    private void initData() {
    }

    private void initListener() {
        fragment_login_register.setOnClickListener(this);
        fragment_login_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_login_register:
                replaceFragment(new RegisterFragment());
                break;
            case R.id.fragment_login_login:
                startToContainerActivity();
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
        fragmentTransaction.replace(R.id.activity_main_frameLayout,fragment).commit();
    }

    private void startToContainerActivity() {
        //TODO 判断数据库中的账号密码是否正确，正确后跳转并退出Activity，错误提示
        Intent intent = new Intent(getActivity(), ContainerActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}