package com.example.dev_wang.databindingdemo.Module.Login;

import com.example.dev_wang.databindingdemo.Module.Login.ViewModel.RunsUserLoginViewModel;
import com.example.dev_wang.databindingdemo.Module.Login.Meditaor.RunsUserLoginMediator;
import com.example.dev_wang.databindingdemo.Module.Login.Meditaor.RunsUserRegisterMediator;

import Runs.Module;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserLoginModule extends Module {

    @Override
    public void onRegister() {

        this.registerMediatorClass(RunsUserLoginMediator.class);
        this.registerMediatorClass(RunsUserRegisterMediator.class);
        //
        this.registerViewModelClass(RunsUserLoginViewModel.class);
    }

    @Override
    public void onRemove() {
        this.removeAllMediator();
        this.removeAllViewModel();
    }
}
