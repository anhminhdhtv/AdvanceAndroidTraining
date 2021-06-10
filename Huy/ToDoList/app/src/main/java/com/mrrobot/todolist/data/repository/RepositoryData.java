package com.mrrobot.todolist.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mrrobot.todolist.data.local.db.AppDatabase;
import com.mrrobot.todolist.data.local.db.dao.TodoDao;
import com.mrrobot.todolist.data.model.db.Todo;

import java.util.List;

public class RepositoryData {
    private TodoDao mTodoDao;
    private LiveData<List<Todo>> mAllTodo;

    public RepositoryData(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTodoDao = db.getTodoDAO();
        mAllTodo = mTodoDao.loadAllTodo();
    }

    public LiveData<List<Todo>> getAllTodo(){
        return mAllTodo;
    }

    public void insertTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mTodoDao.insert(todo);
        });
    }
    public void deleteTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mTodoDao.delete(todo);
        });
    }
    public void updateTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mTodoDao.update(todo);
        });
    }
}
