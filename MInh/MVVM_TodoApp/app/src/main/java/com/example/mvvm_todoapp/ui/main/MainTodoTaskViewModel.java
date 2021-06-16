package com.example.mvvm_todoapp.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.Resource;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;
//import com.example.mvvm_todoapp.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainTodoTaskViewModel extends ViewModel {
    private final LiveData<List<TodoTask>> mTodoTaskList;
    private final TodoTaskRepository mTodoTaskRepository;

    @Inject
    public MainTodoTaskViewModel(TodoTaskRepository todoTaskRepository) {
        this.mTodoTaskRepository = todoTaskRepository;
        LiveData<Resource<List<TodoTask>>> dataList = this.mTodoTaskRepository.getAllTodoTasks();
        mTodoTaskList = Transformations.switchMap(dataList, data -> {
            if(data == null){
                return new MutableLiveData<>(null);
            } else {
                return new MutableLiveData<>(data.data);
            }
        } );
    }
    public LiveData<List<TodoTask>> getAllTodoTasks() {
        return mTodoTaskList;
    }

    public void deleteTask(String taskID) {
        mTodoTaskRepository.deleteTask(taskID);
    }
}
