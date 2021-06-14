package com.example.mvvm_todoapp.ui.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;

public abstract class BaseViewModel extends AndroidViewModel {
    private final TodoTaskRepository mTodoTaskRepository;

    public BaseViewModel(Application application, TodoTaskRepository todoTaskRepository) {
        super(application);
        mTodoTaskRepository = todoTaskRepository;
    }

    public TodoTaskRepository getTodoTaskRepository() {
        return mTodoTaskRepository;
    }

    public void deleteTask(String taskID) {
        getTodoTaskRepository().deleteTask(taskID);
    }

    private LiveData<TodoTask> mTodoTask;

    public void loadTodoTaskByID(String ID){
        if(mTodoTask != null){
            mTodoTask = null;
        }
        mTodoTask = mTodoTaskRepository.getTaskByID(ID);
    }

    public LiveData<TodoTask>  getTodoTask() {
        return mTodoTask;
    }

    public LiveData<TodoTask> getTodoTaskById(String ID){
        return mTodoTaskRepository.getTaskByID(ID);
    }
}
