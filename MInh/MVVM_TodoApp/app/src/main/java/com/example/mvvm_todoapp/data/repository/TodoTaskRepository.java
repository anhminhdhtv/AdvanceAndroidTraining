package com.example.mvvm_todoapp.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.db.TodoTaskDatabase;
import com.example.mvvm_todoapp.data.db.dao.TodoTaskDao;
import com.example.mvvm_todoapp.data.model.TodoTask;

import java.util.List;

public class TodoTaskRepository {
    private volatile static TodoTaskRepository INSTANCE = null;
    private TodoTaskDao mTodoTaskDao;

    private TodoTaskRepository(Application application) {
        TodoTaskDatabase db = TodoTaskDatabase.getDatabase(application);
        mTodoTaskDao = db.todoTaskDao();
    }

    public static TodoTaskRepository getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (TodoTaskRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TodoTaskRepository(application);
                }
            }
        }
        return INSTANCE;
    }


    public LiveData<List<TodoTask>> getAllTodoTasks() {
        return mTodoTaskDao.getAllTask();
    }

    public LiveData<TodoTask> getTaskByID(int ID) {
        return mTodoTaskDao.getTaskByID(ID);
    }

    public void insertTodoTask(TodoTask todoTask) {
        TodoTaskDatabase.databaseWriteExecutor.execute(()->{
            mTodoTaskDao.insertTask(todoTask);
        });
    }

    public void insertTodoTasks(TodoTask... todoTasks) {
        mTodoTaskDao.insertTasks(todoTasks);
    }

    public void update(TodoTask todoTask) {
        mTodoTaskDao.update(todoTask);
    }

    public void deleteAll() {
        mTodoTaskDao.deleteAllTask();
    }

    public void deleteTask(int taskID) {
        TodoTaskDatabase.databaseWriteExecutor.execute(()->{
            mTodoTaskDao.deleteTask(taskID);
        });
    }
}
