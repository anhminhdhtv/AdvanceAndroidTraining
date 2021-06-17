package com.mrrobot.mvvm_todolist.data.db;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.mrrobot.mvvm_todolist.data.db.dao.TodoDao;
import com.mrrobot.mvvm_todolist.data.model.Todo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao getTodoDAO();

    public AppDatabase() {
    }
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

//    private static volatile com.mrrobot.mvvm_todolist.data.db.AppDatabase INSTANCE;
//    private static final int NUMBER_OF_THREADS = 4;
//    public static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    public static com.mrrobot.mvvm_todolist.data.db.AppDatabase getDatabase(Application context){
//        if(INSTANCE==null){
//            synchronized (com.mrrobot.mvvm_todolist.data.db.AppDatabase.class){
//                if(INSTANCE==null){
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            com.mrrobot.mvvm_todolist.data.db.AppDatabase.class, "myDatabase")
//                            .build();
//                }
//
//            }
//        }
//        return INSTANCE;
//    }


//    private static Callback sRoomDatabaseCallback = new Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                TodoDao dao = INSTANCE.getTodoDAO();
//                dao.deleteAll();
//
//                Todo word = new Todo("0","Name","Now","Note");
//                dao.insert(word);
//            });
//        }
//    };
}
