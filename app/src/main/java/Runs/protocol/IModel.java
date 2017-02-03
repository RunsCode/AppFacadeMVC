package Runs.protocol;

/**
 * Created by dev_wang on 2017/1/24.
 */

public interface IModel {
    void registerViewModel(String key, Class cls);
    void removeViewModel(String key);

    IViewModel getViewModel(String key);
    boolean hasViewModel(String key);

}
