package com.mrrobot.mvvm_todolist.ui.main.apdapter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.utils.AppContainer;

import java.util.List;

public class MainViewModel extends ViewModel {
    LiveData<List<Todo>> listTodo;
    private RepositoryData repositoryData;
    public MainViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
        this.listTodo = repositoryData.getAllTodoList();
    }

    public LiveData<List<Todo>> getAllTodoList(){
        return listTodo;
    }

    public LiveData<Todo> getTodoById(String id){
        return repositoryData.getTodoById(id);
    }

    public  void insertTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repositoryData.insertTodo(todo);
            }
        });

    }

    public void deleteTodo(Todo todo){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repositoryData.deleteTodo(todo);
            }
        });
        //return getAllTodoList();
    }
}
