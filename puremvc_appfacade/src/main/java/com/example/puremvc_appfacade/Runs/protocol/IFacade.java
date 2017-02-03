package com.example.puremvc_appfacade.Runs.protocol;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface IFacade extends INotifier {

    void init(IController controller);

    void notifyObservers(INotification notification);

    void registerMediatorWithName(String name, Class cls);
    void removeMediatorWithName(String name);

    void registerViewModelWithName(String name, Class cls);
    void removeViewModelWithName(String name);

    IMediator getMediatorWithName(String name);
    IViewModel getViewModelWithName(String name);

    boolean hasMediator(String name);
    boolean hasViewModel(String name);

    void registerAllModules();
    void unRegisterAllModules();

    IModule getModule(String moduleName);

}
