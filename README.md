# PureMVC_AppFacade_Android
<font color="#272727" size = "3px">这库是一个轻量级MVC为Android软件从头开始架构的，特点是轻、解耦、模块化，业务分离，简单实用</font>

<font color="#4590a3" size = "3px">This library is a lightweight MVC architecture for Android software from the ground up, featuring light, decoupling, modular, business separation, simple and practical.</font>

<font color="#272727" size = "3px">该库的设计主要有 Facade、Observer等，以及热插拔特性，充分给了我开发者注重业务开发逻辑注意力，而不用在意逻辑的分离与耦合</font>

<font color="#4590a3" size = "3px">The design of the library are Facade, Observer, etc., and hot-swap features, and give me the full attention of developers focus on business development logic, rather than care about the separation and coupling logic.</font>

<font color="#000000" size = "6px">How To Get Started：</font>
<font color="#4590a3" size = "4px">第一步 初始化自定义模块控制类：用于注册模块和解注册模块</font>

<a name="AppModuleController">AppModuleController：</a>
初始化和创建 <font color="#4590a3" size = "3px">AppModuleController</font>， 它继承自 <font color="#4590a3" size = "3px">Controller</font>
```java
public class AppModuleController extends Controller {
    @Override
    public void registerAllModules() {
        super.registerAllModules();
        //这里注册对应模块
        this.addOnceModuleClass(RunsUserLoginModule.class);
        this.addOnceModuleClass(RunsHomePageModule.class);
    }  
    
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
        Facade.INSTANCE.init(new AppModuleController());
    }
}
```

<font color="#4590a3" size = "4px">第二步 创建模块文件夹目录（如图）</font>

至于每个模块分别是什么职责一目了然

![F04F6A3FB1343515D617810B1681E63F.jpg](./puremvc_appfacade/image/F04F6A3FB1343515D617810B1681E63F.jpg)

![QQ20170206-120213.png](./puremvc_appfacade/image/QQ20170206-120213.png)

<font color="#4590a3" size = "4px">第三步 创建对应的功能模块，比如登录、主页等</font>
<a name="RunsUserLoginModule">RunsUserLoginModule</a>
初始化和创建 <font color="#4590a3" size = "3px">RunsUserLoginModule</font>, 它继承自 <font color="#4590a3" size = "3px">Module</font>, 注册该模块需要的<font color="#4590a3" size = "3px">Mediator,ViewModel</font>, 以及移除解注册.
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
```java
package com.example.dev_wang.databindingdemo.Module.Login.Meditaor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.dev_wang.databindingdemo.MainActivity;
import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserLoginActivity;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserRegisterActivity;
import com.example.dev_wang.databindingdemo.Module.Login.Callback.RequestCallback;
import com.example.dev_wang.databindingdemo.Module.Login.Model.RunsUserInfo;
import com.example.dev_wang.databindingdemo.Module.Login.ViewModel.RunsUserLoginViewModel;
import com.example.puremvc_appfacade.Runs.Facade;
import com.example.puremvc_appfacade.Runs.Mediator;
import com.example.puremvc_appfacade.Runs.protocol.INotification;


/**
 * Created by dev_wang on 2017/1/24.
 */

public class RunsUserLoginMediator extends Mediator {

    @Override
    public void initializeMediator() {
        super.initializeMediator();
    }

    @Override
    public void handleNotification(INotification notification) {
        super.handleNotification(notification);
        String notificationName = notification.getName();

        //绑定注入的activity 从而接管activity 对象
        if (notificationName.equals(Runs.BIND_VIEW_COMPONENT)) {
            Object object = notification.getObject();
            if (null != object) {
                this.setViewComponent(object);
                Toast.makeText((Context)object, Runs.BIND_VIEW_COMPONENT, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (notificationName.equals(Runs.USER_LOGIN_NOTIFICATION)) {
/*
       RunsUserLoginViewModel 是数据逻辑处理类 网络请求 数据持久化 序列化  适配转换等
       流程：
       1. mediator 到 ViewModel 拿数据给 activity展示
       2. activity 接受用户事件 发送给mediator 视图逻辑处理  
       3.（有简单视图无数据处理切换等，有网络请求通过mediator 去拿数据 然后就是流程1） 反馈给activity展示

       String className = RunsUserLoginViewModel.class.getName();
       IViewModel viewModel = Facade.INSTANCE.getViewModelWithName(className);
       String viewModelName = viewModel.getViewModelName();
       Log.i("viewModelName", viewModelName);

       这里拿到对应ViewModel 就可以拿到数据 然后给对应activity展示
*/
            String className = RunsUserLoginViewModel.class.getName();
            RunsUserLoginViewModel viewModel = (RunsUserLoginViewModel)Facade.INSTANCE.getViewModelWithName(className);

            final  MainActivity activity = (MainActivity)notification.getObject();

            viewModel.requestHttpInfo("我要请求用户信息", new RequestCallback<RunsUserInfo>() {
                @Override
                public void success(RunsUserInfo response) {
                    Log.i(TAG,"requestHttpInfo success， context = " + activity);
                    Toast.makeText(activity, Runs.USER_LOGIN_NOTIFICATION, Toast.LENGTH_SHORT).show();
                    pushLoginActivity(activity, response);
                }

                @Override
                public void error(Error err) {
                    Toast.makeText(activity, "请求数据失败", Toast.LENGTH_SHORT).show();
                }
            });

            return;
        }

        if (notificationName.equals(Runs.USER_REGISTER_NOTIFICATION)){
            Activity activity = (Activity)(this.getViewComponent());
            Intent intent = new Intent(activity, RunsUserRegisterActivity.class);
            activity.startActivity(intent);
            return;
        }

        if (notificationName.equals(Runs.USER_LOGOUT_NOTIFICATION)) {
            Toast.makeText((Context)this.getViewComponent(), Runs.USER_LOGOUT_NOTIFICATION, Toast.LENGTH_SHORT).show();
            Activity activity = (Activity)(this.getViewComponent());
            activity.finish();
            return;
        }

        //解除绑定注入的activity
        if (notificationName.equals(Runs.UNBUNDLED_VIEW_COMPONENT)) {
            Context context = (Context)this.getViewComponent();
            Toast.makeText(context, Runs.BIND_VIEW_COMPONENT, Toast.LENGTH_SHORT).show();
            this.setViewComponent(null);
            return;
        }
    }

    private void pushLoginActivity(Activity activity, RunsUserInfo info){
        Log.i(TAG,"pushLoginActivity");
        Intent intent = new Intent(activity, RunsUserLoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{
                Runs.BIND_VIEW_COMPONENT,
                Runs.USER_LOGIN_NOTIFICATION,
                Runs.USER_LOGOUT_NOTIFICATION,
                Runs.USER_REGISTER_NOTIFICATION,
                Runs.UNBUNDLED_VIEW_COMPONENT};
    }
}

```

```java
public abstract class Runs {


public abstract class Runs {

    //RunsUserLoginMediator
    public static final String BIND_VIEW_COMPONENT = "BIND_VIEW_COMPONENT";
    public static final String UNBUNDLED_VIEW_COMPONENT = "UNBUNDLED_VIEW_COMPONENT";
    public static final String USER_LOGIN_NOTIFICATION = "USER_LOGIN_NOTIFICATION";
    public static final String USER_LOGOUT_NOTIFICATION = "USER_LOGOUT_NOTIFICATION";
    public static final String USER_REGISTER_NOTIFICATION = "USER_REGISTER_NOTIFICATION";

    //RunsUserRegisterMediatorUNLOCK
    public static final String BIND_REGISTER_COMPONENT = "BIND_REGISTER_COMPONENT";
    public static final String USER_BIND_PHONE_NOTIFICATION = "USER_BIND_PHONE_NOTIFICATION";
    public static final String USER_REGISTER_DONE_NOTIFICATION = "USER_REGISTER_DONE_NOTIFICATION";

}

```

```java

public class RunsUserLoginViewModel extends ViewModel {
    @Override
    public void setModel(Object model) {
        super.setModel(model);
    }

    @Override
    public void initializeViewModel() {
        super.initializeViewModel();
    }

    public void requestHttpInfo(String parameters, RequestCallback<RunsUserInfo> callback) {
        //假设这一段是请求逻辑
        boolean succ = parameters.length() > 0;
        if (succ) {
            Log.i(TAG,"requestHttpInfo succ");
            callback.success(new RunsUserInfo());
        }else{
            Log.i(TAG,"requestHttpInfo fail");
            callback.error(new Error());
        }
    }
}

```
<font color="#000000" size = "6px">Usage：</font>
 1.根据注册名(Java中反射获取Class字符串)获取对应 **Mediator**（不建议使用）
 ```java
String className = RunsUserLoginMediator.class.getName();
RunsUserLoginMediator mediator = (RunsUserLoginMediator)Runs.FACADE.getMediatorWithName(className);
 ```
 2.根据注册名(Java中反射获取Class字符串)获取对应 **ViewModel**（常用）
```java
String className = RunsUserLoginViewModel.class.getName();
RunsUserLoginViewModel viewModel = (RunsUserLoginViewModel)Facade.INSTANCE.getViewModelWithName(className);

```
 3.核心用法,消息派发.通过**sendNotification**方法，发出的消息会被对应监听的**Mediator**收听到而做对应的逻辑处理（核心）
```java
package com.example.dev_wang.databindingdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dev_wang.databindingdemo.Module.AppFacade.RunsLog;
import com.example.dev_wang.databindingdemo.databinding.ActivityMainBinding;

import com.example.dev_wang.databindingdemo.Module.AppFacade.AppModuleController;
import com.example.dev_wang.databindingdemo.Module.Login.Activity.RunsUserLoginActivity;

import com.example.dev_wang.databindingdemo.Module.AppFacade.Runs;
import com.example.puremvc_appfacade.Runs.Facade;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding = null;

    public MainActivity() {
        super();
        Facade.INSTANCE.init(new AppModuleController());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final MainActivity that = this;
        activityMainBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_LOGIN_NOTIFICATION,that);
                //上面一句代码等同于下面两行
//                Intent intent = new Intent(MainActivity.this, RunsUserLoginActivity.class);
//                startActivity(intent);
            }
        });
        activityMainBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_REGISTER_NOTIFICATION);
            }
        });

        activityMainBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facade.INSTANCE.sendNotification(Runs.USER_LOGOUT_NOTIFICATION);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Facade.INSTANCE.unRegisterAllModules();
    }
}

```
###Download
You can download a jar from GitHub's [releases page](https://github.com/RunsCode/AppFacadeMVC/releases).   
Gradle:
```ruby
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```ruby
	dependencies {
	        compile 'com.github.RunsCode:AppFacadeMVC:v1.0.1'
	}
```
Maven:
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
	<dependency>
	    <groupId>com.github.RunsCode</groupId>
	    <artifactId>AppFacadeMVC</artifactId>
	    <version>v1.0.1</version>
	</dependency>
```


 Security Disclosure

If you believe you have identified a security vulnerability with PureMVC_AppFacade_Android, you should report it as soon as possible via email to dev_wang@foxmail.com. Please do not post it to a public issue tracker.

 License

PureMVC_AppFacade_Android is released under the MIT license. See LICENSE for details.
