package com.example.dev_wang.databindingdemo.Module.Login.Callback;

/**
 * Created by dev_wang on 2017/3/9.
 */

public interface RequestCallback<T> {
    void success(T response);
    void error(Error err);
}
