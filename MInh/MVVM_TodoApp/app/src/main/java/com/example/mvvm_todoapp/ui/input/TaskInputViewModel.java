package com.example.mvvm_todoapp.ui.input;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;
import com.example.mvvm_todoapp.ui.base.BaseViewModel;


public class TaskInputViewModel extends BaseViewModel {

    public ObservableField<String> fragmentTitle = new ObservableField<>("");
    public MutableLiveData<TodoTask> todoTask = new MutableLiveData<>(new TodoTask());

    public TaskInputViewModel(Application application, TodoTaskRepository todoTaskRepository) {
        super(application, todoTaskRepository);
    }

    public void createNewTodoTask() {
        todoTask.setValue(new TodoTask());
    }

    public void insertTodoTask() {
        getTodoTaskRepository().insertTodoTask(todoTask.getValue());
    }

    public void update() {
        getTodoTaskRepository().update(todoTask.getValue());
    }

    public void setFragmentTitle(String strTitle) {
        fragmentTitle.set(strTitle);
    }
}
