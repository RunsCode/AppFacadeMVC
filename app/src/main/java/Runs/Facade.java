package Runs;


import java.util.Objects;

import Runs.core.Model;
import Runs.core.View;
import Runs.observer.Notification;
import Runs.protocol.IController;
import Runs.protocol.IFacade;
import Runs.protocol.IMediator;
import Runs.protocol.IModel;
import Runs.protocol.IModule;
import Runs.protocol.INotification;
import Runs.protocol.IView;
import Runs.protocol.IViewModel;

/**
 * Created by dev_wang on 2017/1/23.
 */

public class Facade implements IFacade {
    protected String TAG = this.getClass().getName();
    private IView iView = new View();
    private IModel iModel = new Model();
    private IController iController = null;

    private static IFacade facade = new Facade();
    public static IFacade getInstance() {
        return facade;
    }

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
