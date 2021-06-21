package com.mrrobot.mvvm_todolist.ui.main.apdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.data.repository.Resource;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    LiveData<List<Todo>> listTodo;
    private RepositoryData repositoryData;

    @Inject
    public MainViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
        //this.listTodo = repositoryData.getAllTodoList();
        LiveData<Resource<List<Todo>>> dataList = this.repositoryData.getAllTodoListFromServer();
        listTodo = Transformations.switchMap(dataList, data -> {
            if(data == null){
                return new MutableLiveData<>(null);
            } else {
                return new MutableLiveData<>(data.data);
            }
        } );
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
