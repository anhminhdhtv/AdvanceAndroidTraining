package com.example.mvvm_todoapp.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.Resource;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;

import javax.inject.Inject;

public class TaskDetailViewModel extends ViewModel {
    private final TodoTaskRepository mTodoTaskRepository;

    @Inject
    public TaskDetailViewModel(TodoTaskRepository todoTaskRepository) {
        this.mTodoTaskRepository = todoTaskRepository;
    }

    public void deleteTask(String taskID) {
        mTodoTaskRepository.deleteTask(taskID);
    }

    private LiveData<TodoTask> mTodoTask;

    public void loadTodoTaskByID(String ID){
        if(mTodoTask != null){
            mTodoTask = null;
        }
        mTodoTask = getTodoTaskById(ID);
    }

    public LiveData<TodoTask>  getTodoTask() {
        return mTodoTask;
    }

    public LiveData<TodoTask> getTodoTaskById(String ID){
        LiveData<Resource<TodoTask>> data = mTodoTaskRepository.getTaskByID(ID);
        return Transformations.switchMap(data, d -> {
            if(d == null){
                return new MutableLiveData<>(null);
            } else {
                return new MutableLiveData<>(d.data);
            }
        });
    }

}
