package com.example.puremvc_appfacade.Runs;

import com.example.puremvc_appfacade.Runs.protocol.IViewModel;

/**
 * Created by dev_wang on 2017/1/23.
 */

public class ViewModel implements IViewModel {
    protected String TAG = this.getClass().getName();
    private Object model = null;
    private String name = null;

    @Override
    public void initializeViewModel() {
        // will override by sub class
    }

    @Override
    public void onRegister() {
        // will override by sub class
    }

    @Override
    public void onRemove() {
        // will override by sub class
    }

    @Override
    public void setModel(Object model) {
        this.model = model;
    }

    @Override
    public Object getModel() {
        return model;
    }

    @Override
    public void setViewModelName(String name) {
        this.name = name;
    }

    @Override
    public String getViewModelName() {
        return name;
    }
}
