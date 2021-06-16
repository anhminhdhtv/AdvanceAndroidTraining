package com.example.mvvm_todoapp.di.component;

import android.app.Application;

import com.example.mvvm_todoapp.di.modules.AppModule;
import com.example.mvvm_todoapp.di.modules.NetworkModule;
import com.example.mvvm_todoapp.ui.detail.TaskDetailFragment;
import com.example.mvvm_todoapp.ui.input.TaskInputFragment;
import com.example.mvvm_todoapp.ui.main.MainFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MainFragment fragment);

    void inject(TaskDetailFragment fragment);

    void inject(TaskInputFragment fragment);
}
