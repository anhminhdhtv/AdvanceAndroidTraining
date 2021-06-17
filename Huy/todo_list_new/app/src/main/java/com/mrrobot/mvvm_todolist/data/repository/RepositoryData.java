package com.mrrobot.mvvm_todolist.data.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.db.dao.TodoDao;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.remote.ApiResponse;
import com.mrrobot.mvvm_todolist.data.remote.RetrofitRequest;
import com.mrrobot.mvvm_todolist.data.remote.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryData {
    private TodoDao todoDao;
    private LiveData<List<Todo>> mListTodo;
    Service service ;

    public RepositoryData(TodoDao todoDao, Service service){
        this.service = service;
        this.todoDao = todoDao;
    }

    public LiveData<List<Todo>> getAllTodoList(){
        LiveData<Resource<List<Todo>>> dataList = getAllTodoListFromServer();
        mListTodo = Transformations.switchMap(dataList,data->{
            if(data == null){
                return todoDao.loadAllTodo();
            } else {
                return new MutableLiveData<>(data.data);
            }
        });

        return mListTodo;
    }
    public void insertTodo(Todo todo){
//        AppDatabase.databaseWriteExecutor.execute(()->{
//            todoDao.insert(todo);
//        });
    }
    public void deleteTodo(Todo todo){
//        AppDatabase.databaseWriteExecutor.execute(()->{
//            todoDao.delete(todo);
//        });
    }
    public void updateTodo(Todo todo){
//        AppDatabase.databaseWriteExecutor.execute(()-> todoDao.update(todo));
    }

    public LiveData<Todo> getTodoById(String id){
        return todoDao.getTaskByID(id);
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

}
