package com.mrrobot.mvvm_todolist.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.db.dao.TodoDao;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.remote.ApiResponse;
import com.mrrobot.mvvm_todolist.data.remote.Service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepositoryData {
    private TodoDao todoDao;

    LiveData<Todo> todo;
    Service service ;

    @Inject
    public RepositoryData(TodoDao todoDao, Service service){
        this.service = service;
        this.todoDao = todoDao;
    }

    public void insertTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.insert(todo);
            }
        });
    }
    public void deleteTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.delete(todo);
            }
        });

    }
    public void updateTodo(Todo todo){
        todoDao.update(todo);
    }

    public LiveData<Todo> getTodoById(String id){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todo = todoDao.getTaskByID(id);
            }
        });
        return todo;
    }

    public LiveData<Resource<List<Todo>>> getAllTodoListFromServer(){
        return new NetworkBoundResource<List<Todo>, List<Todo>>() {
            @Override
            protected void saveCallResult(@NonNull List<Todo> item) {
                todoDao.insertTasks(item);
            }

            @NonNull
            @Override
            protected LiveData<List<Todo>> loadFromDb() {
                return todoDao.getAllTask();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Todo>>> createCall() {
                return service.getAllTasks();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Todo>> getAllTodoWithID(String id){
        return new NetworkBoundResource<Todo, Todo>() {
            @Override
            protected void saveCallResult(@NonNull Todo item) {
                //todoDao.insert(item);
            }

            @NonNull
            @Override
            protected LiveData<Todo> loadFromDb() {
                return todoDao.getTaskByID(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Todo>> createCall() {
                return service.getTaskByID(id);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Todo>> deleteTodoTask(String id){
        return new NetworkBoundResource<Todo, Todo>() {
            @Override
            protected void saveCallResult(@NonNull Todo item) {
                todoDao.delete(getAllTodoWithID(id).getValue().data);
            }

            @NonNull
            @Override
            protected LiveData<Todo> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Todo>> createCall() {
                return service.deleteTask(id);
            }
        }.getAsLiveData();
    }

}
