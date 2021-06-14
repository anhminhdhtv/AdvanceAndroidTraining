package com.example.mvvm_todoapp.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;
import com.example.mvvm_todoapp.ui.base.BaseViewModel;

import java.util.List;

public class MainTodoTaskViewModel extends BaseViewModel {
    private final LiveData<List<TodoTask>> mTodoTaskList;

    public MainTodoTaskViewModel(Application application, TodoTaskRepository todoTaskRepository) {
        super(application, todoTaskRepository);
        mTodoTaskList = getTodoTaskRepository().getAllTodoTasks();
    }
    public LiveData<List<TodoTask>> getAllTodoTasks() {
        return mTodoTaskList;
    }

}
