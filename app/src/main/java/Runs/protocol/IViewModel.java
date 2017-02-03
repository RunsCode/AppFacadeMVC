package Runs.protocol;

/**
 * Created by dev_wang on 2017/1/23.
 */

public interface IViewModel {
    void  setModel(Object model);
    Object getModel();

    void  setViewModelName(String name);
    String getViewModelName();

    void onRegister();
    void onRemove();
    void initializeViewModel();
}
