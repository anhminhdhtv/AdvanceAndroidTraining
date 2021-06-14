package com.example.mvvm_todoapp.ui.base;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;
import com.example.mvvm_todoapp.ui.detail.TaskDetailViewModel;
import com.example.mvvm_todoapp.ui.input.TaskInputViewModel;
import com.example.mvvm_todoapp.ui.main.MainTodoTaskViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final TodoTaskRepository mTodoTasksRepository;

    private ViewModelFactory(Application application, TodoTaskRepository repository) {
        mApplication = application;
        mTodoTasksRepository = repository;
    }

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application, TodoTaskRepository.getInstance(application));
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainTodoTaskViewModel.class)) {
            return (T) new MainTodoTaskViewModel(mApplication, mTodoTasksRepository);
        } else if (modelClass.isAssignableFrom(TaskDetailViewModel.class)) {
            return (T) new TaskDetailViewModel(mApplication, mTodoTasksRepository);
        } else if (modelClass.isAssignableFrom(TaskInputViewModel.class)) {
            return (T) new TaskInputViewModel(mApplication, mTodoTasksRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
