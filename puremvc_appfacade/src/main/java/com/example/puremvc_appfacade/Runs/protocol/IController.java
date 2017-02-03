package com.example.puremvc_appfacade.Runs.protocol;

/**
 * Created by dev_wang on 2017/1/24.
 */

public interface IController {
    void registerAllModules();
    void unRegisterAllModules();

    void addOnceModuleClass(Class moduleClass);
    void removeModule(String moduleName);
    IModule getModule(String moduleName);
}
