package com.example.mvvm_todoapp.di.modules;

import android.app.Application;


import androidx.room.Room;

import com.example.mvvm_todoapp.data.db.TodoTaskDatabase;
import com.example.mvvm_todoapp.data.db.dao.TodoTaskDao;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class AppModule {
    @Singleton
    @Provides
    public TodoTaskDatabase provideTodoTaskDatabase(Application application){
        return Room.databaseBuilder(application,
                            TodoTaskDatabase.class, "todo_database")
                            .allowMainThreadQueries()
                            .build();
    }

    @Singleton
    @Provides
    public TodoTaskDao provideTodoTaskDao(TodoTaskDatabase todoTaskDatabase){
        return todoTaskDatabase.todoTaskDao();
    }
}
