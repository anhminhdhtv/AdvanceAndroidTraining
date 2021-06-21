package com.mrrobot.mvvm_todolist;

import android.app.Application;

import com.mrrobot.mvvm_todolist.di.component.AppComponent;
import com.mrrobot.mvvm_todolist.di.component.DaggerAppComponent;
import com.mrrobot.mvvm_todolist.utils.AppContainer;

public class MyApplication extends Application {
    public AppContainer appContainer;
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
        mAppComponent = DaggerAppComponent.builder().application(this).build();
    }
    public AppComponent getComponent(){
        return mAppComponent;
    }
}
