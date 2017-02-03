package com.example.dev_wang.databindingdemo.Module.Login.Activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dev_wang.databindingdemo.R;
import com.example.dev_wang.databindingdemo.databinding.ActivityRegisterBinding;

import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.puremvc_appfacade.Runs.Facade;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserRegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        Facade.getInstance().sendNotification(Runs.BIND_REGISTER_COMPONENT, this);
        activityRegisterBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activityRegisterBinding.bingPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runs.FACADE.sendNotification(Runs.USER_BIND_PHONE_NOTIFICATION);
            }
        });


        activityRegisterBinding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runs.FACADE.sendNotification(Runs.USER_REGISTER_DONE_NOTIFICATION);
            }
        });
    }
}
