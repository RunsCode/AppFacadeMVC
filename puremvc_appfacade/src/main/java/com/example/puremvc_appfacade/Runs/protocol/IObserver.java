package com.example.puremvc_appfacade.Runs.protocol;

import java.lang.reflect.Method;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface IObserver {

    boolean comparedNotifyContext(Object context);
    void setNotifyContext(Object context);
    void notifyObserver(INotification notification);
    void setNotifyMethod(Method method);
}
