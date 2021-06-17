package com.mrrobot.mvvm_todolist.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mrrobot.mvvm_todolist.MainActivity;
import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.utils.AppContainer;

public class TaskDetailViewModel extends AndroidViewModel {

    LiveData<Todo> todo;
    private RepositoryData repositoryData;

    public TaskDetailViewModel(@NonNull Application application) {
        super(application);
        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        this.repositoryData = appContainer.repositoryData;
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
}
