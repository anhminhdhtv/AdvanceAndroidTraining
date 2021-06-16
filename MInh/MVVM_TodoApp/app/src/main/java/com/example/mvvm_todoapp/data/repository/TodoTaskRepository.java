package com.example.mvvm_todoapp.data.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.db.TodoTaskDatabase;
import com.example.mvvm_todoapp.data.db.dao.TodoTaskDao;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.remote.ApiResponse;
import com.example.mvvm_todoapp.data.remote.Service;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TodoTaskRepository {
    private final TodoTaskDao mTodoTaskDao;
    private final Service mTodoService;

    @Inject
    public TodoTaskRepository(TodoTaskDao todoTaskDao, Service mTodoService) {
        mTodoTaskDao = todoTaskDao;
        this.mTodoService = mTodoService;
    }

    public LiveData<Resource<List<TodoTask>>> getAllTodoTasks() {
        return new NetworkBoundResource<List<TodoTask>, List<TodoTask>>(){

            @Override
            protected void saveCallResult(@NonNull List<TodoTask> item) {
                mTodoTaskDao.insertTasks(item);
            }

            @NonNull
            @Override
            protected LiveData<List<TodoTask>> loadFromDb() {
                return mTodoTaskDao.getAllTask();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<TodoTask>>> createCall() {
                return mTodoService.getAllTasks();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<TodoTask>> getTaskByID(String ID) {
        return new NetworkBoundResource<TodoTask, TodoTask>(){

            @Override
            protected void saveCallResult(@NonNull TodoTask item) {
                mTodoTaskDao.insertTask(item);
            }

            @NonNull
            @Override
            protected LiveData<TodoTask> loadFromDb() {
                return mTodoTaskDao.getTaskByID(ID);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<TodoTask>> createCall() {
                return mTodoService.getTaskByID(ID);
            }
        }.getAsLiveData();
    }

    public void insertTodoTask(TodoTask todoTask) {
        TodoTaskDatabase.databaseWriteExecutor.execute(()->{
            mTodoTaskDao.insertTask(todoTask);
        });
    }


    public void update(TodoTask todoTask) {
        mTodoTaskDao.update(todoTask);
    }

    public void deleteAll() {
        mTodoTaskDao.deleteAllTask();
    }

    public void deleteTask(String taskID) {
        TodoTaskDatabase.databaseWriteExecutor.execute(()->{
            mTodoTaskDao.deleteTask(taskID);
        });
    }
}
