package com.example.tools_design.Activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.tools_design.Activity.activity.ContainerActivity;
import com.example.tools_design.Model.Model;
import com.example.tools_design.Model.bean.UserInfo;
import com.example.tools_design.R;
import com.example.tools_design.Utils.Logs;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText fragment_login_userName;
    private EditText fragment_login_password;
    private TextView fragment_login_register;
    private Button fragment_login_login;
    private ImageView fragment_login_icon;
    private View view;

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
        fragment_login_icon = view.findViewById(R.id.fragment_login_icon);
    }

    private void initData() {
        try {
            Bundle bundle = getArguments();
            if (bundle != null) {
                fragment_login_userName.setText(bundle.getString("userName"));
                fragment_login_password.setText(bundle.getString("userPassword"));
            }
        } catch (Exception e) {
            Logs.i("Cannot find bundle of login information");
        }

        Glide.with(LoginFragment.this).load(R.drawable.login_icon).into(fragment_login_icon);
    }

    private void initListener() {
        fragment_login_register.setOnClickListener(this);
        fragment_login_login.setOnClickListener(this);

        //TODO 当用户名和密码都输入之后，再显示可点击按钮
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_login_register:
                replaceFragment(new RegisterFragment());
                break;
            case R.id.fragment_login_login:
                loginIn();
                break;

        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        fragmentTransaction.replace(R.id.activity_main_frameLayout, fragment).commit();
    }

    private void loginIn() {
        String username = fragment_login_userName.getText().toString();
        String password = fragment_login_password.getText().toString();
        Model.getInstance().getGlobalThreadPool().execute(() -> {
            UserInfo userInfo = Model.getInstance().getUserDao().getUserInformationByUserName(username);
            if (userInfo != null) {
                if (password.equals(userInfo.getPassword())) {
                    Model.getInstance().getUserDao().loginIn(username);
                    requireActivity().runOnUiThread(() -> {
                        Intent intent = new Intent(getActivity(), ContainerActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    });
                } else {
                    requireActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "密码输入错误!", Toast.LENGTH_SHORT).show());
                }
            } else {
                requireActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "不存在该用户!", Toast.LENGTH_SHORT).show());
            }
        });

    }
}