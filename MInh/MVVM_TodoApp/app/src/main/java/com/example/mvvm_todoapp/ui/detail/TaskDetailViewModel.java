package com.example.mvvm_todoapp.ui.detail;

import android.app.Application;

import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;
import com.example.mvvm_todoapp.ui.base.BaseViewModel;

public class TaskDetailViewModel extends BaseViewModel {
    public TaskDetailViewModel(Application application, TodoTaskRepository todoTaskRepository) {
        super(application, todoTaskRepository);
    }

}
