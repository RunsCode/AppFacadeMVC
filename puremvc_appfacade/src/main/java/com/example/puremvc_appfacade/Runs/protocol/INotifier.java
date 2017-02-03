package com.example.puremvc_appfacade.Runs.protocol;

import java.util.Objects;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface INotifier {
    void sendNotification(String name);
    void sendNotification(String name, Object body);
    void sendNotification(String name, String type);
    void sendNotification(String name, Object body, String type);
}
