package com.example.puremvc_appfacade.Runs.protocol;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface IMediator {
    String Method_Name = "handleNotification";

    void onRegister();
    void onRemove();

    void setViewComponent(Object viewComponent);
    Object getViewComponent();

    void setMediatorName(String name);
    String getMediatorName();

    void initializeMediator();
    void handleNotification(INotification notification);
    String[] listNotificationInterests();
}
