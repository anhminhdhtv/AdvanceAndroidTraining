package com.mrrobot.mvvm_todolist.di.component;

import android.app.Application;

import com.mrrobot.mvvm_todolist.di.module.AppModule;
import com.mrrobot.mvvm_todolist.di.module.NetworkModule;
import com.mrrobot.mvvm_todolist.ui.detail.DetailFragment;
import com.mrrobot.mvvm_todolist.ui.input.InputFragment;
import com.mrrobot.mvvm_todolist.ui.main.MainFragment;

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

    void inject(DetailFragment fragment);

    void inject(InputFragment fragment);
}
