package Runs.protocol;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface INotification {

    void setName(String name);
    String getName();

    void setObject(Object object);
    Object getObject();

    void setType(String type);
    String getType();
}
