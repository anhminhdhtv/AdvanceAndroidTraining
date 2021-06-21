package com.mrrobot.mvvm_todolist.di.module;

import android.app.Application;

import androidx.room.Room;

import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.db.dao.TodoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class AppModule {
    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Application application){
        return Room.databaseBuilder(application,
                AppDatabase.class, "todo_database")
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public TodoDao provideTodoTaskDao(AppDatabase todoTaskDatabase){
        return todoTaskDatabase.getTodoDAO();
    }
}
