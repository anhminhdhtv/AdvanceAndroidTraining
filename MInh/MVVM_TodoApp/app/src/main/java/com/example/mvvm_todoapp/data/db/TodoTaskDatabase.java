package com.example.mvvm_todoapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
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
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile TodoTaskDatabase INSTANCE;

    public static TodoTaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodoTaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoTaskDatabase.class, "todo_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TodoTaskDao todoTaskDao();
}
