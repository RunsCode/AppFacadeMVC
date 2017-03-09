package com.example.dev_wang.databindingdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dev_wang.databindingdemo.Module.AppFacade.RunsLog;
import com.example.dev_wang.databindingdemo.databinding.ActivityMainBinding;

import com.example.dev_wang.databindingdemo.Module.AppFacade.AppModuleController;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserLoginActivity;

import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.puremvc_appfacade.Runs.Facade;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding = null;

    public MainActivity() {
        super();
        Facade.INSTANCE.init(new AppModuleController());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final MainActivity that = this;
        activityMainBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_LOGIN_NOTIFICATION,that);
                //上面一句代码等同于下面两行
//                Intent intent = new Intent(MainActivity.this, RunsUserLoginActivity.class);
//                startActivity(intent);
            }
        });
        activityMainBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_REGISTER_NOTIFICATION);
            }
        });

        activityMainBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_LOGOUT_NOTIFICATION);
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Facade.INSTANCE.unRegisterAllModules();
    }
}
