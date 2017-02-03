package com.example.dev_wang.databindingdemo.Module.Login.Meditaor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserLoginActivity;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserRegisterActivity;

import Runs.Mediator;
import Runs.protocol.INotification;

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

        if (notificationName.equals(Runs.BIND_VIEW_COMPONENT)) {
            Object object = notification.getObject();
            if (null != object) {
                this.setViewComponent(object);
                Toast.makeText((Context)object, Runs.BIND_VIEW_COMPONENT, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (notificationName.equals(Runs.USER_LOGIN_NOTIFICATION)) {
            Context context = (Context)this.getViewComponent();
            Toast.makeText(context, Runs.USER_LOGIN_NOTIFICATION, Toast.LENGTH_SHORT).show();
            //
            Activity activity = (Activity)(this.getViewComponent());
            Intent intent = new Intent(activity, RunsUserLoginActivity.class);
            activity.startActivity(intent);
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

    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{Runs.BIND_VIEW_COMPONENT, Runs.USER_LOGIN_NOTIFICATION, Runs.USER_LOGOUT_NOTIFICATION, Runs.USER_REGISTER_NOTIFICATION };
    }
}
