package com.example.puremvc_appfacade.Runs;


import com.example.puremvc_appfacade.Runs.core.Model;
import com.example.puremvc_appfacade.Runs.core.View;
import com.example.puremvc_appfacade.Runs.observer.Notification;
import com.example.puremvc_appfacade.Runs.protocol.IController;
import com.example.puremvc_appfacade.Runs.protocol.IFacade;
import com.example.puremvc_appfacade.Runs.protocol.IModel;
import com.example.puremvc_appfacade.Runs.protocol.INotification;
import com.example.puremvc_appfacade.Runs.protocol.IView;

import com.example.puremvc_appfacade.Runs.protocol.IMediator;
import com.example.puremvc_appfacade.Runs.protocol.IModule;
import com.example.puremvc_appfacade.Runs.protocol.IViewModel;

/**
 * Created by dev_wang on 2017/1/23.
 */

public enum Facade implements IFacade {
    INSTANCE;
    private IView iView = new View();
    private IModel iModel = new Model();
    private IController iController = null;

    @Override
    public void init(IController controller) {
        this.iController = controller;
        this.registerAllModules();
    }

    @Override
    public void notifyObservers(INotification notification) {
        iView.notifyObservers(notification);
    }

    @Override
    public void sendNotification(String name) {
        sendNotification(name, null, null);
    }

    @Override
    public void sendNotification(String name, Object body) {
        sendNotification(name, body, null);
    }

    @Override
    public void sendNotification(String name, String type) {
        sendNotification(name, null, type);
    }

    @Override
    public void sendNotification(String name, Object body, String type) {
        this.notifyObservers(Notification.init(name, body, type));
    }

    @Override
    public void registerMediatorWithName(String name, Class cls) {
        iView.registerMediator(name, cls);
    }

    @Override
    public void removeMediatorWithName(String name) {
        iView.removeMediator(name);
    }

    @Override
    public void registerViewModelWithName(String name, Class cls) {
        iModel.registerViewModel(name, cls);
    }

    @Override
    public void removeViewModelWithName(String name) {
        iModel.removeViewModel(name);
    }

    @Override
    public IMediator getMediatorWithName(String name) {
        return iView.getMediator(name);
    }

    @Override
    public IViewModel getViewModelWithName(String name) {
        return iModel.getViewModel(name);
    }

    @Override
    public boolean hasMediator(String name) {
        return iView.hasMediator(name);
    }

    @Override
    public boolean hasViewModel(String name) {
        return iModel.hasViewModel(name);
    }

    @Override
    public void registerAllModules() {
        iController.registerAllModules();
    }

    @Override
    public void unRegisterAllModules() {
        iController.unRegisterAllModules();
    }

    @Override
    public IModule getModule(String moduleName) {
        return iController.getModule(moduleName);
    }

}
