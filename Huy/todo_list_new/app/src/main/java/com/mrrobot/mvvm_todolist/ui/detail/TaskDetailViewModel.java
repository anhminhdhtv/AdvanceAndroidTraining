package com.mrrobot.mvvm_todolist.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;

public class TaskDetailViewModel extends ViewModel {

    public LiveData<Todo> todo;
    private RepositoryData repositoryData;

    public TaskDetailViewModel(RepositoryData repositoryData) {
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
}
