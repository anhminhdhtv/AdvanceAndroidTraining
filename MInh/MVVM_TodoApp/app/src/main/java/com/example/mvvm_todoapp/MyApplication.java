package com.example.mvvm_todoapp;

import android.app.Application;

import com.example.mvvm_todoapp.di.component.AppComponent;
import com.example.mvvm_todoapp.di.DaggerAppComponent;

public class MyApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().application(this).build();
    }
    public AppComponent getComponent(){
        return mAppComponent;
    }
}
