package com.mrrobot.mvvm_todolist;

import android.app.Application;

import com.mrrobot.mvvm_todolist.utils.AppContainer;

public class MyApplication extends Application {
    public AppContainer appContainer = new AppContainer(MyApplication.this);
}
