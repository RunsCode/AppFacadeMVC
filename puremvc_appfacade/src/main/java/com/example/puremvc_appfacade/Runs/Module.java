package com.example.puremvc_appfacade.Runs;

import com.example.puremvc_appfacade.Runs.protocol.IFacade;

import java.util.ArrayList;

import com.example.puremvc_appfacade.Runs.protocol.IModule;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class Module implements IModule {
    protected String TAG = this.getClass().getName();
    private ArrayList<String> mediators = new ArrayList<>();
    private ArrayList<String> viewModels = new ArrayList<>();
    private IFacade facade = null;
    private String name = null;

    @Override
    public void onRegister() {
        // will override by sub class
    }

    @Override
    public void onRemove() {
        // will override by sub class
    }

    public void registerMediatorClass(Class iMediatorClass) {
        String mediatorName = iMediatorClass.getName();
        if (mediators.contains(mediatorName)) {
            return;
        }
        mediators.add(mediatorName);
        this.facade.registerMediatorWithName(mediatorName, iMediatorClass);

    }
    public void registerViewModelClass(Class iViewModelClass) {
        String viewModelName = iViewModelClass.getName();
        if (viewModels.contains(viewModelName)){
            return;
        }
        viewModels.add(viewModelName);
        this.facade.registerViewModelWithName(viewModelName, iViewModelClass);
    }

    public void removeAllMediator(){
        for (String name : mediators) {
            this.facade.removeMediatorWithName(name);
        }
        mediators.clear();

    }
    public void removeAllViewModel(){
        for (String name : viewModels) {
            this.facade.removeViewModelWithName(name);
        }
        viewModels.clear();
    }


    @Override
    public void setModuleName(String name) {
        this.name = name;
    }

    @Override
    public String getModuleName() {
        return this.name;
    }

    public Module() {
        this.facade = Facade.getInstance();
    }
}
