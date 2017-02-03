package com.example.puremvc_appfacade.Runs.observer;

import com.example.puremvc_appfacade.Runs.protocol.INotification;

/**
 * Created by dev_wang on 2017/1/23.
 */

public class Notification implements INotification {
    private String name = null;
    private String type = null;
    private Object object = null;

    public Notification(String name, Object object, String type) {
        this.name = name;
        this.object = object;
        this.type = type;
    }

    public static INotification init(String name, Object object, String type) {
        return new Notification(name, object, type);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
