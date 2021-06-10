package com.example.mvvm_todoapp.ui.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;

import java.util.List;

public class TodoTaskViewModel extends AndroidViewModel {
   // private final LiveData<List<TodoTask>> mAllTodoTasks;
    private TodoTaskRepository mTodoTaskRepository;

    public TodoTaskViewModel(Application application) {
        super(application);
        mTodoTaskRepository = new TodoTaskRepository(application);
    }

    public LiveData<List<TodoTask>> getAllTodoTasks() {
        return mTodoTaskRepository.getAllTodoTasks();
    }
    public LiveData<TodoTask> getTodoTaskByID(int ID) {
        return mTodoTaskRepository.getTaskByID(ID);
    }

    public void insertTodoTask(TodoTask todoTask) {
        mTodoTaskRepository.insertTodoTask(todoTask);
    }

    public void insertTodoTasks(TodoTask... todoTasks) {
        mTodoTaskRepository.insertTodoTasks(todoTasks);
    }

    public void update(TodoTask todoTask) {
        mTodoTaskRepository.update(todoTask);
    }

    public void deleteAll() {
        mTodoTaskRepository.deleteAll();
    }

    public void deleteTask(int taskID) {
        mTodoTaskRepository.deleteTask(taskID);
    }
}
