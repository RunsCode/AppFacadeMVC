package com.example.dev_wang.databindingdemo.Module.Login.Meditaor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.dev_wang.databindingdemo.MainActivity;
import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.puremvc_appfacade.Runs.Mediator;
import com.example.puremvc_appfacade.Runs.protocol.INotification;


/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserRegisterMediator extends Mediator {

    @Override
    public void initializeMediator() {
        super.initializeMediator();
    }

    @Override
    public void handleNotification(INotification notification) {
        super.handleNotification(notification);

        String notificationName = notification.getName();

        if (notificationName.equals(Runs.BIND_REGISTER_COMPONENT)) {
            Object object = notification.getObject();
            if (null != object) {
                this.setViewComponent(object);
                Toast.makeText((Context)object, Runs.BIND_REGISTER_COMPONENT, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (notificationName.equals(Runs.USER_REGISTER_DONE_NOTIFICATION)) {
            Activity activity = (Activity)(this.getViewComponent());
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            return;
        }

        if (notificationName.equals(Runs.USER_BIND_PHONE_NOTIFICATION)) {
            Toast.makeText((Context)getViewComponent(), Runs.USER_BIND_PHONE_NOTIFICATION, Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{Runs.BIND_REGISTER_COMPONENT, Runs.USER_BIND_PHONE_NOTIFICATION,Runs.USER_REGISTER_DONE_NOTIFICATION};
    }
}
