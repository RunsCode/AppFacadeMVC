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
        Facade.getInstance().init(new AppModuleController());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Runs.FACADE.sendNotification(Runs.BIND_VIEW_COMPONENT, this);

        activityMainBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runs.FACADE.sendNotification(Runs.USER_LOGIN_NOTIFICATION);
                Intent intent = new Intent(MainActivity.this, RunsUserLoginActivity.class);
                startActivity(intent);
            }
        });

        activityMainBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runs.FACADE.sendNotification(Runs.USER_REGISTER_NOTIFICATION);
            }
        });

        activityMainBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runs.FACADE.sendNotification(Runs.USER_LOGOUT_NOTIFICATION);
            }
        });

        RunsLog.v("吧吧吧吧吧吧吧");
        RunsLog.d("吧吧吧吧吧吧吧");
        RunsLog.i("吧吧吧吧吧吧吧");
        RunsLog.w("吧吧吧吧吧吧吧");
        RunsLog.e("吧吧吧吧吧吧吧");
//        String className = RunsUserLoginViewModel.class.getName();
//        IViewModel viewModel = Runs.FACADE.getViewModelWithName(className);
//        String viewModelName = viewModel.getViewModelName();
//        Log.i("viewModelName", viewModelName);

//        String className = RunsUserLoginMediator.class.getName();
//        RunsUserLoginMediator mediator = (RunsUserLoginMediator)Runs.FACADE.getMediatorWithName(className);
//        mediator.setViewComponent(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Facade.getInstance().unRegisterAllModules();


    }
}
