package com.example.mvvm_todoapp.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mvvm_todoapp.data.db.converter.DateConverter;
import com.example.mvvm_todoapp.data.db.dao.TodoTaskDao;
import com.example.mvvm_todoapp.data.model.TodoTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TodoTask.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)

public abstract class TodoTaskDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract TodoTaskDao todoTaskDao();
}
