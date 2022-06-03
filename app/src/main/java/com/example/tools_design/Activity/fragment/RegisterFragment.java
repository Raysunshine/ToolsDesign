package com.example.tools_design.Activity.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tools_design.Model.Model;
import com.example.tools_design.Model.bean.UserInfo;
import com.example.tools_design.R;
import com.example.tools_design.Utils.Constant;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageButton fragment_register_backButton;
    private EditText fragment_register_userName;
    private EditText fragment_register_password;
    private EditText fragment_register_confirmPassword;
    private Button fragment_register_register;
    private ImageView fragment_register_icon;

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
        fragment_register_backButton = view.findViewById(R.id.fragment_register_backButton);
        fragment_register_userName = view.findViewById(R.id.fragment_register_userName);
        fragment_register_password = view.findViewById(R.id.fragment_register_password);
        fragment_register_confirmPassword = view.findViewById(R.id.fragment_register_confirmPassword);
        fragment_register_register = view.findViewById(R.id.fragment_register_register);
        fragment_register_icon = view.findViewById(R.id.fragment_register_icon);

    }

    private void initData() {
        Glide.with(RegisterFragment.this).load(R.drawable.login_icon).into(fragment_register_icon);
    }

    private void initListener() {
        fragment_register_backButton.setOnClickListener(this);
        fragment_register_register.setOnClickListener(this);

        //TODO 当用户名和密码都输入之后，再显示可点击按钮
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_register_backButton:
                replaceFragment(new LoginFragment(), false);
                break;
            case R.id.fragment_register_register:
                register();
                break;
        }
    }

    private void register() {
        String userName = fragment_register_userName.getText().toString();
        String password = fragment_register_password.getText().toString();
        String confirmPassword = fragment_register_confirmPassword.getText().toString();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                boolean isExisted = Model.getInstance().getUserDao().isUsernameExisted(userName);
                if (isExisted) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "该用户已存在!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (password.equals(confirmPassword)) {
                        UserInfo userInfo = new UserInfo(userName, password, 0);
                        Model.getInstance().getUserDao().addUser(userInfo);
                        sendRegisterSuccessNotification();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                                replaceFragment(new LoginFragment(), true);
                            }
                        });
                    }else{
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }

    private void sendRegisterSuccessNotification() {
        //TODO 自动发送一个通知,Title:注册成功，Container:账号,密码，图标:app_icon
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void replaceFragment(Fragment fragment, boolean isSetArguments) {
        if (isSetArguments) {
            Bundle bundle = new Bundle();
            bundle.putString("userName", fragment_register_userName.getText().toString());
            bundle.putString("userPassword", fragment_register_password.getText().toString());
            fragment.setArguments(bundle);
        }
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        fragmentTransaction.replace(R.id.activity_main_frameLayout, fragment).commit();
    }
}