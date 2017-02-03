package Runs.protocol;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface IModule {
    void onRegister();
    void onRemove();

    void setModuleName(String name);
    String getModuleName();
}
