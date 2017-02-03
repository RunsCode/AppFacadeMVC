package com.example.dev_wang.databindingdemo.Module.AppFacade;

import com.example.dev_wang.databindingdemo.Module.Home.RunsHomePageModule;
import com.example.dev_wang.databindingdemo.Module.Login.RunsUserLoginModule;
import com.example.puremvc_appfacade.Runs.core.Controller;


/**
 * Created by dev_wang on 2017/1/24.
 */

public class AppModuleController extends Controller {
    @Override
    public void registerAllModules() {
        super.registerAllModules();
        this.addOnceModuleClass(RunsUserLoginModule.class);
        this.addOnceModuleClass(RunsHomePageModule.class);
    }

    @Override
    public void unRegisterAllModules() {
        super.unRegisterAllModules();
        this.removeModule(RunsUserLoginModule.class.getName());
        this.removeModule(RunsHomePageModule.class.getName());
    }
}
