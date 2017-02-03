package Runs.core;

import android.util.Log;

import java.util.HashMap;

import Runs.protocol.IModel;
import Runs.protocol.IViewModel;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class Model implements IModel {
    protected String TAG = Controller.class.getName();
    private HashMap<String, IViewModel> iViewModelMap = new HashMap<>();

    @Override
    public void registerViewModel(String key, Class cls) {
        if (null == key || cls == null) {
            Log.e(TAG, "Register ViewModel with " + key +" failed" + " class: " + cls);
            return;
        }

        if (hasViewModel(key)) {
            Log.e(TAG, "The ViewModel has already existed");
            return;
        }

        try {

            IViewModel viewModel = (IViewModel)cls.newInstance();
            viewModel.setViewModelName(key);
            iViewModelMap.put(key, viewModel);
            viewModel.onRegister();

        } catch (Exception ex) {
            Log.e(TAG, "Exception : Register ViewModel with " + key + " failed");
        }
    }

    @Override
    public void removeViewModel(String key) {
        if (null == key) {
            Log.e(TAG, "Remove ViewModel with failed ,name is null");
            return;
        }
        if (!hasViewModel(key)) {
            Log.e(TAG, "Remove ViewModel with " + key + " failed");
            return;
        }

        IViewModel viewModel = iViewModelMap.remove(key);
        if (null != viewModel) {
            viewModel.onRemove();
        }
    }

    @Override
    public IViewModel getViewModel(String key) {
        if (null == key) {
            Log.e(TAG, "Get ViewModel with failed ,name is null");
            return null;
        }
        if (!hasViewModel(key)) {
            Log.e(TAG, "Get ViewModel with " + key + " failed");
            return  null;
        }
        return iViewModelMap.get(key);
    }

    @Override
    public boolean hasViewModel(String key) {
        return iViewModelMap.containsKey(key);
    }
}
