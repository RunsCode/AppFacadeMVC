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

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        activityLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runs.FACADE.sendNotification(Runs.USER_REGISTER_NOTIFICATION);
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
