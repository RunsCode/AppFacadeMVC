package com.example.dev_wang.databindingdemo.Module.Login.ViewModel;


import android.util.Log;

import com.example.dev_wang.databindingdemo.Module.Login.Callback.RequestCallback;
import com.example.dev_wang.databindingdemo.Module.Login.Model.RunsUserInfo;
import com.example.puremvc_appfacade.Runs.ViewModel;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserLoginViewModel extends ViewModel {
    @Override
    public void setModel(Object model) {
        super.setModel(model);
    }

    @Override
    public void initializeViewModel() {
        super.initializeViewModel();
    }

    public void requestHttpInfo(String parameters, RequestCallback<RunsUserInfo> callback) {
        //假设这一段是请求逻辑
        boolean succ = parameters.length() > 0;
        if (succ) {
            Log.i(TAG,"requestHttpInfo succ");
            callback.success(new RunsUserInfo());
        }else{
            Log.i(TAG,"requestHttpInfo fail");
            callback.error(new Error());
        }
    }
}
