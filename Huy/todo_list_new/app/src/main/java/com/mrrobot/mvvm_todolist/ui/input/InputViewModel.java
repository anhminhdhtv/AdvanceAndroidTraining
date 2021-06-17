package com.mrrobot.mvvm_todolist.ui.input;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.data.repository.Resource;
import com.mrrobot.mvvm_todolist.utils.AppContainer;

public class InputViewModel extends ViewModel {
    public LiveData<Todo> todo;
    private RepositoryData repositoryData;
    public MutableLiveData<Todo> todoTask = new MutableLiveData<>(new Todo());
    public InputViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    public LiveData<Todo> loadTodoById(String id){
        todo = repositoryData.getTodoById(id);
        return todo;
    }

    public LiveData<Todo>  getTodoTask() {
        return todo;
    }

    public void deleteTodo(Todo todo){
        repositoryData.deleteTodo(todo);

    }
    public void update() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repositoryData.updateTodo(todoTask.getValue());
            }
        });

    }

    public LiveData<Todo> getTodoByID(String ID){
        LiveData<Resource<Todo>> data = repositoryData.getAllTodoWithID(ID);
        return Transformations.switchMap(data, d -> {
            if(d == null){
                return new MutableLiveData<>(null);
            } else {
                return new MutableLiveData<>(d.data);
            }
        });

    }

    public void createNewTodoTask() {
        todoTask.setValue(new Todo());
    }


    public void insertTodo(){
        repositoryData.insertTodo(todoTask.getValue());
    }
}
