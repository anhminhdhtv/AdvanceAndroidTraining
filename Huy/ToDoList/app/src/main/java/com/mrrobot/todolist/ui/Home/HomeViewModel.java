package com.mrrobot.todolist.ui.Home;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrrobot.todolist.data.model.db.Todo;
import com.mrrobot.todolist.data.repository.RepositoryData;
import com.mrrobot.todolist.ui.splash.SplashActivity;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    LiveData<List<Todo>> listTodo;
    MutableLiveData<Todo> selected = new MutableLiveData<>();
    MutableLiveData<Integer> typeAction = new MutableLiveData<>();
    private final RepositoryData repositoryData;

    public HomeViewModel(Application application) {
        super(application);
        this.repositoryData = new RepositoryData(application);
        this.listTodo = repositoryData.getAllTodo();
    }



    public LiveData<List<Todo>> getListTodo() {
        return listTodo;
    }

    public LiveData<List<Todo>> deleteTodo(Todo todo){
        repositoryData.deleteTodo(todo);
        listTodo=repositoryData.getAllTodo();
        return listTodo;
    }
    public LiveData<List<Todo>> insertTodo(Todo todo){
        repositoryData.insertTodo(todo);
        listTodo=repositoryData.getAllTodo();
        return listTodo;
    }

    public LiveData<List<Todo>> updateTodo(Todo todo){
        repositoryData.updateTodo(todo);
        listTodo=repositoryData.getAllTodo();
        return listTodo;
    }

    public void selectItem(Todo todo){
        selected.setValue(todo);
    }

    public LiveData<Todo> getSelected(){
        return selected;
    }

    public MutableLiveData<Integer> getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(Integer typeAction) {
        this.typeAction.setValue(typeAction);
    }
}