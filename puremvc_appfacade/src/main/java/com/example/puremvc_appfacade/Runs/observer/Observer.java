package com.example.puremvc_appfacade.Runs.observer;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.example.puremvc_appfacade.Runs.protocol.INotification;
import com.example.puremvc_appfacade.Runs.protocol.IObserver;

/**
 * Created by dev_wang on 2017/1/23.
 */

public class Observer implements IObserver{
    private String Tag = this.getClass().getName();

    private Method method = null;
    private Object context = null;

    public static IObserver withNotifyMethod(Method method, Object context) {
        return new Observer(method, context);
    }

    public Observer(Method method, Object context) {
        this.method = method;
        this.context = context;
    }

    @Override
    public boolean comparedNotifyContext(Object context) {
        return this.context.equals(context);
    }

    @Override
    public void setNotifyContext(Object context) {
        this.context = context;
    }

    @Override
    public void setNotifyMethod(Method method) {
        this.method = method;
    }

    @Override
    public void notifyObserver(INotification notification) {
        if (null == context) {
            Log.e(Tag, "Notify observer failed content is null");
            return;
        }

        if (null == method) {
            Log.e(Tag, "Notify observer failed method is null");
            return;
        }

        if (null == notification) {
            Log.e(Tag, "Notify observer failed notification is null");
            return;
        }
        try {
            method.invoke(context, notification);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
