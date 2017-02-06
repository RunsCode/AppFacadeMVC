# PureMVC_AppFacade_Android

#####<font color="#272727" size = "3px">这是库是一个轻量级MVC为Android软件从头开始架构的，特点是轻、解耦、模块化，业务分离，简单实用</font>

#####<font color="#4590a3" size = "3px">This library is a lightweight MVC architecture for Android software from the ground up, featuring light, decoupling, modular, business separation, simple and practical.</font>

#####<font color="#272727" size = "3px">该库的设计主要有 Facade、Observer等，以及热插拔特性，充分给了我开发者注重业务开发逻辑注意力，而不用在意逻辑的分离与耦合</font>

#####<font color="#4590a3" size = "3px">The design of the library are Facade, Observer, etc., and hot-swap features, and give me the full attention of developers focus on business development logic, rather than care about the separation and coupling logic.</font>

####<font color="#000000" size = "6px">Usage：</font>
#####<font color="#4590a3" size = "4px">第一步 初始化自定义模块控制类：用于注册模块和解注册模块</font>

#### <a name="AppModuleController">AppModuleController：</a>
######初始化和创建 <font color="#4590a3" size = "3px">AppModuleController</font>， 它继承自 <font color="#4590a3" size = "3px">Controller</font>
```java
public class AppModuleController extends Controller {
    @Override
    public void registerAllModules() {
        super.registerAllModules();
        //这里注册对应模块
        this.addOnceModuleClass(RunsUserLoginModule.class);
        this.addOnceModuleClass(RunsHomePageModule.class);
    }

    @Override
    public void unRegisterAllModules() {
        super.unRegisterAllModules();
        //这里解注册对应模块（最好位置与注册对应）
        this.removeModule(RunsUserLoginModule.class.getName());
        this.removeModule(RunsHomePageModule.class.getName());
    }
}
```
```java
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding = null;
    public MainActivity() {
        super();
        //初始化调用 注册模块到模块管理类
        Facade.getInstance().init(new AppModuleController());
    }
}
```

#####<font color="#4590a3" size = "4px">第二步 创建模块文件夹目录（如图）</font>

######至于每个模块分别是什么职责一目了然

![F04F6A3FB1343515D617810B1681E63F.jpg](./puremvc_appfacade/image/F04F6A3FB1343515D617810B1681E63F.jpg)

![QQ20170206-120213.png](./puremvc_appfacade/image/QQ20170206-120213.png)

#####<font color="#4590a3" size = "4px">第三步 创建对应的功能模块，比如登录、主页等</font>
#### <a name="RunsUserLoginModule">RunsUserLoginModule</a>
######初始化和创建 <font color="#4590a3" size = "3px">RunsUserLoginModule</font>, 它继承自 <font color="#4590a3" size = "3px">Module</font>, 注册该模块需要的<font color="#4590a3" size = "3px">Mediator,ViewModel</font>, 以及移除解注册.
```java
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
```
