package Runs.observer;

import java.util.Objects;

import Runs.Facade;
import Runs.protocol.INotifier;

/**
 * Created by dev_wang on 2017/1/23.
 */

public class Notifier implements INotifier {

    @Override
    public void sendNotification(String name) {
        this.sendNotification(name, null, null);
    }

    @Override
    public void sendNotification(String name, Object body) {
        this.sendNotification(name, body, null);
    }

    @Override
    public void sendNotification(String name, String type) {
        this.sendNotification(name, null, type);
    }

    @Override
    public void sendNotification(String name, Object body, String type) {
        Facade.getInstance().sendNotification(name, body, type);
    }
}
