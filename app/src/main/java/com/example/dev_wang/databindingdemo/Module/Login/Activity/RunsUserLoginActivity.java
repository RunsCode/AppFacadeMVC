package com.example.dev_wang.databindingdemo.Module.Login.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.dev_wang.databindingdemo.Module.AppFacade.RunsLog;
import com.example.dev_wang.databindingdemo.R;
import com.example.dev_wang.databindingdemo.databinding.ActivityLoginBinding;

import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.puremvc_appfacade.Runs.Facade;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserLoginActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private ActivityLoginBinding activityLoginBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //给对应mediator 注入 activity
        Facade.INSTANCE.sendNotification(Runs.BIND_VIEW_COMPONENT, this);


        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_REGISTER_NOTIFICATION);
            }
        });

        activityLoginBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String fileLineMethod = RunsLog.TAG();
        String file = RunsLog._FILE_();
        String func = RunsLog._FUNC_();
        String line = RunsLog._LINE_() + "";
        String time = RunsLog._TIME_();
        Log.i(TAG, file + "|" + func + "|" + line + "|" +time);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity  销毁的时候接触绑定释放对象 引用计数减一
        Facade.INSTANCE.sendNotification(Runs.UNBUNDLED_VIEW_COMPONENT);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
