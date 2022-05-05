package com.dqgb.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dqgb.mvvm.databinding.ActivityLoginBinding;
import com.dqgb.mvvm.lib_network.ApiResponse;
import com.dqgb.mvvm.lib_network.ApiService;
import com.dqgb.mvvm.lib_network.JsonCallBack;
import com.dqgb.mvvm.lib_network.Request;
import com.dqgb.mvvm.livedata.LiveDataBus;
import com.dqgb.mvvm.model.Feed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kotlin.jvm.internal.TypeReference;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //不是 jetpack 的设置xml
//        setContentView(R.layout.activity_login);

//        jetpack 设置xml 方法一
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

//        jetpack 设置xml 方法二
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        //初始化viewModel
        viewModel =new ViewModelProvider(this).get(LoginViewModel.class);
        //观察者 观察网络请求
        LiveDataBus.get().with("feedList").observe(this, new Observer<List<Feed> >() {
            @Override
            public void onChanged(List<Feed> feeds) {
                //更新UI
                if(!feeds.isEmpty()){

                }
            }
        });
    }

    private void initView() {
        String userName = binding.inputItemUsername.toString();
        String password = binding.inputItemPassword.toString();
        if (TextUtils.isEmpty(userName) || (TextUtils.isEmpty(password))) {
            Toast.makeText(this,"账号或密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        binding.actionLogin.setOnClickListener(view -> {
            //登录
            viewModel.toLogin();
        });
    }


}