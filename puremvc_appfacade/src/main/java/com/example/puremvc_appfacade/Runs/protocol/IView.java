package com.example.puremvc_appfacade.Runs.protocol;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface IView {
    void registerMediator(String key, Class cls);
    void removeMediator(String key);

    IMediator getMediator(String key);
    boolean hasMediator(String key);

    void notifyObservers(INotification notification);
    void registerObserver(IObserver observer, String notificationName);
    void removeObserver(Object context, String notificationName);
}
