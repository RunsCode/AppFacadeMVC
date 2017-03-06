package com.example.puremvc_appfacade.Runs.core;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.puremvc_appfacade.Runs.observer.Observer;
import com.example.puremvc_appfacade.Runs.protocol.IMediator;
import com.example.puremvc_appfacade.Runs.protocol.INotification;
import com.example.puremvc_appfacade.Runs.protocol.IObserver;
import com.example.puremvc_appfacade.Runs.protocol.IView;

/**
 * Created by dev_wang on 2017/1/23.
 */

public class View implements IView{
    protected String TAG = Controller.class.getName();
    private HashMap<String, IMediator> iMediatorMap = new HashMap<>();
    private HashMap<String, ArrayList<IObserver>> iObserverMap = new HashMap<>();

    @Override
    public void notifyObservers(INotification notification) {
        ArrayList<IObserver> observers = iObserverMap.get(notification.getName());
        ArrayList<IObserver> workingObservers = new ArrayList<>();
        if (null == observers) {
            return;
        }
        //反转消息派发顺序 先发先收
        for (IObserver observer : observers) {
            workingObservers.add(observer);
        }
        for (IObserver observer : workingObservers) {
            observer.notifyObserver(notification);
        }
    }

    @Override
    public void registerMediator(String key, Class cls) {
        if (null == key || cls == null) {
            Log.e(TAG, "Register mediator with " + key +" failed" + " class: " + cls);
            return;
        }

        if (hasMediator(key)) {
            Log.e(TAG, "The mediator has already existed");
            return;
        }

        IMediator mediator;
        try {
            mediator = (IMediator)cls.newInstance();
            mediator.setMediatorName(key);
            iMediatorMap.put(key, mediator);
        } catch (Exception ex) {
            Log.e(TAG, "Exception : Register mediator with " + key + " failed");
            return;
        }

        String notificationList[] = mediator.listNotificationInterests();
        if (notificationList.length <= 0) {
            return;
        }

        try {
            Method method = cls.getMethod(mediator.Method_Name, INotification.class);
            IObserver observer =  Observer.withNotifyMethod(method, mediator);
            for (int i = 0; i < notificationList.length; i++) {
                this.registerObserver(observer, notificationList[i]);
            }
            mediator.onRegister();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeMediator(String key) {
        if (null == key) {
            Log.e(TAG, "Remove Mediator with failed ,name is null");
            return;
        }
        if (!hasMediator(key)) {
            Log.e(TAG, "Remove Mediator with " + key + " failed");
            return;
        }
        IMediator mediator = iMediatorMap.remove(key);
        if (null == mediator) {
            return;
        }

        String notificationList[] = mediator.listNotificationInterests();
        for (String notificationName : notificationList) {
            this.removeObserver(mediator, notificationName);
        }
        mediator.onRemove();
    }


    @Override
    public void registerObserver(IObserver observer, String notificationName) {
        ArrayList<IObserver> observers = iObserverMap.get(notificationName);
        if (null == observers || observers.size() <= 0) {
            observers = new ArrayList<>();
            iObserverMap.put(notificationName, observers);
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(Object context, String notificationName) {
        ArrayList<IObserver> observers = iObserverMap.get(notificationName);
        if (null == observers || observers.size() <= 0) {
            return;
        }
        for (IObserver observer : observers) {
            if (observer.comparedNotifyContext(context)){
                observers.remove(observer);
                break;
            }
        }
        if (observers.size() == 0) {
            iObserverMap.remove(notificationName);
        }
    }

    @Override
    public IMediator getMediator(String key) {
        if (null == key) {
            Log.e(TAG, "Get Mediator with failed ,name is null");
            return null;
        }

        if (!hasMediator(key)) {
            Log.e(TAG, "Get Mediator with " + key + " failed");
            return  null;
        }
        return iMediatorMap.get(key);
    }

    @Override
    public boolean hasMediator(String key) {
        return iMediatorMap.containsKey(key);
    }
}
