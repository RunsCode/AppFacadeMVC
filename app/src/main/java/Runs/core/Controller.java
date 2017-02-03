package Runs.core;

import android.util.Log;

import java.util.HashMap;

import Runs.protocol.IController;
import Runs.protocol.IModule;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class Controller implements IController {
    protected String TAG = Controller.class.getName();

    public HashMap<String, IModule> moduleHashMap = new HashMap<>();

    @Override
    public void registerAllModules() {
        // will override by sub class
    }

    @Override
    public void unRegisterAllModules() {
        // will override by sub class
    }

    @Override
    public void addOnceModuleClass(Class moduleClass) {
        String name = moduleClass.getName();
        if (moduleHashMap.containsKey(name)){
            return;
        }

        IModule module;
        try {
            module = (IModule)moduleClass.newInstance();
            module.setModuleName(name);
            moduleHashMap.put(name, module);
            module.onRegister();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        Log.i(TAG, "注册 module :" + name );
    }

    @Override
    public void removeModule(String moduleName) {
        moduleHashMap.remove(moduleName);
    }

    @Override
    public IModule getModule(String moduleName) {
        return moduleHashMap.get(moduleName);
    }

}
