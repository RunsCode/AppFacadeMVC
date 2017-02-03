package Runs;

import android.util.Log;
import Runs.protocol.IMediator;
import Runs.protocol.INotification;

/**
 * Created by dev_wang on 2017/1/24.
 */

public class Mediator implements IMediator {
    protected String TAG = this.getClass().getName();
    private Object viewComponent = null;
    private String mediatorName = null;

    @Override
    public void initializeMediator() {
        Log.i(TAG,"initializeMediator " + this.getClass().getName());
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
    public void setViewComponent(Object viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Override
    public Object getViewComponent() {
        return this.viewComponent;
    }

    @Override
    public void setMediatorName(String name) {
        this.mediatorName = name;
    }

    @Override
    public String getMediatorName() {
        return this.mediatorName;
    }

    @Override
    public void handleNotification(INotification notification) {
        // will override by sub class
        Log.i(TAG,"handleNotification " + this.getClass().getName());
    }

    @Override
    public String[] listNotificationInterests() {
        return null;
    }
}
