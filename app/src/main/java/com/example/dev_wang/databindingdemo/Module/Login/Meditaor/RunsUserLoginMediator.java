package com.example.dev_wang.databindingdemo.Module.Login.Meditaor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.dev_wang.databindingdemo.MainActivity;
import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserLoginActivity;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserRegisterActivity;
import com.example.dev_wang.databindingdemo.Module.Login.Callback.RequestCallback;
import com.example.dev_wang.databindingdemo.Module.Login.Model.RunsUserInfo;
import com.example.dev_wang.databindingdemo.Module.Login.ViewModel.RunsUserLoginViewModel;
import com.example.puremvc_appfacade.Runs.Facade;
import com.example.puremvc_appfacade.Runs.Mediator;
import com.example.puremvc_appfacade.Runs.protocol.INotification;


/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserLoginMediator extends Mediator {

    @Override
    public void initializeMediator() {
        super.initializeMediator();
    }

    @Override
    public void handleNotification(INotification notification) {
        super.handleNotification(notification);
        String notificationName = notification.getName();

        //绑定注入的activity 从而接管activity 对象
        if (notificationName.equals(Runs.BIND_VIEW_COMPONENT)) {
            Object object = notification.getObject();
            if (null != object) {
                this.setViewComponent(object);
                Toast.makeText((Context)object, Runs.BIND_VIEW_COMPONENT, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (notificationName.equals(Runs.USER_LOGIN_NOTIFICATION)) {
/*
       RunsUserLoginViewModel 是数据逻辑处理类 网络请求 数据持久化 序列化  适配转换等
       流程：1. mediator 到 ViewModel 拿数据给 activity展示
       2. activity 接受用户事件 发送给mediator 视图逻辑处理（有简单视图无数据处理切换等，有网络请求通过mediator 去拿数据 然后就是流程1） 反馈给activity展示

       String className = RunsUserLoginViewModel.class.getName();
       IViewModel viewModel = Facade.INSTANCE.getViewModelWithName(className);
       String viewModelName = viewModel.getViewModelName();
       Log.i("viewModelName", viewModelName);

       这里拿到对应ViewModel 就可以拿到数据 然后给对应activity展示
*/
            String className = RunsUserLoginViewModel.class.getName();
            RunsUserLoginViewModel viewModel = (RunsUserLoginViewModel)Facade.INSTANCE.getViewModelWithName(className);

            final  MainActivity activity = (MainActivity)notification.getObject();
            Log.i(TAG,Runs.USER_LOGIN_NOTIFICATION + "， activity = " + activity);
            final  Context context = (Context)activity;//activity.getApplicationContext();
            Log.i(TAG,Runs.USER_LOGIN_NOTIFICATION + "， context = " + context);

            viewModel.requestHttpInfo("我要请求用户信息", new RequestCallback<RunsUserInfo>() {
                @Override
                public void success(RunsUserInfo response) {
                    Log.i(TAG,"requestHttpInfo success， context = " + activity);
                    Toast.makeText(activity, Runs.USER_LOGIN_NOTIFICATION, Toast.LENGTH_SHORT).show();
                    pushLoginActivity(activity,response);
                }

                @Override
                public void error(Error err) {
                    Toast.makeText(activity, "请求数据失败", Toast.LENGTH_SHORT).show();
                }
            });

            return;
        }

        if (notificationName.equals(Runs.USER_REGISTER_NOTIFICATION)){
            Activity activity = (Activity)(this.getViewComponent());
            Intent intent = new Intent(activity, RunsUserRegisterActivity.class);
            activity.startActivity(intent);
            return;
        }

        if (notificationName.equals(Runs.USER_LOGOUT_NOTIFICATION)) {
            Toast.makeText((Context)this.getViewComponent(), Runs.USER_LOGOUT_NOTIFICATION, Toast.LENGTH_SHORT).show();
            Activity activity = (Activity)(this.getViewComponent());
            activity.finish();
            return;
        }

        //接触绑定注入的activity
        if (notificationName.equals(Runs.UNBUNDLED_VIEW_COMPONENT)) {
            Context context = (Context)this.getViewComponent();
            Toast.makeText(context, Runs.BIND_VIEW_COMPONENT, Toast.LENGTH_SHORT).show();
            this.setViewComponent(null);
            return;
        }
    }

    private void pushLoginActivity(Activity activity, RunsUserInfo info){
        Log.i(TAG,"pushLoginActivity");
        Intent intent = new Intent(activity, RunsUserLoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{
                Runs.BIND_VIEW_COMPONENT,
                Runs.USER_LOGIN_NOTIFICATION,
                Runs.USER_LOGOUT_NOTIFICATION,
                Runs.USER_REGISTER_NOTIFICATION,
                Runs.UNBUNDLED_VIEW_COMPONENT};
    }
}
