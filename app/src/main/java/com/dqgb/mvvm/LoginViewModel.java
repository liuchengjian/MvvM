package com.dqgb.mvvm;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.dqgb.mvvm.lib_network.ApiResponse;
import com.dqgb.mvvm.lib_network.ApiService;
import com.dqgb.mvvm.lib_network.JsonCallBack;
import com.dqgb.mvvm.lib_network.Request;
import com.dqgb.mvvm.livedata.LiveDataBus;
import com.dqgb.mvvm.model.Feed;

import java.util.Collections;
import java.util.List;

import kotlin.jvm.internal.TypeReference;

public class LoginViewModel extends ViewModel {

    //登录的网络逻辑
    public void toLogin() {
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedType", 0)
                .addParam("userId", "1631678065")
                .addParam("feedId", 0)
                .addParam("pageCount", 10)
                .responseType(new TypeReference<List<Feed>>() {
                }.getType());
        request.execute(new JsonCallBack<List<Feed>>() {
            @Override
            public void onSuccess(ApiResponse<List<Feed>> response) {
                super.onSuccess(response);
                Log.e("loadData", "onCacheSuccess: ");
                List<Feed> data = response.body == null ? Collections.emptyList() : (List<Feed>) response.body;
                LiveDataBus.get().with("feedList")
                        .postValue(data);
            }
        });
    }


}
