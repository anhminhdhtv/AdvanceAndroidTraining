package com.example.mvvm_todoapp.ui.input;


import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.repository.Resource;
import com.example.mvvm_todoapp.data.repository.TodoTaskRepository;

import javax.inject.Inject;


public class TaskInputViewModel extends ViewModel {
    private final TodoTaskRepository mTodoTaskRepository;

    public ObservableField<String> fragmentTitle = new ObservableField<>("");
    public MutableLiveData<TodoTask> todoTask = new MutableLiveData<>(new TodoTask());

    @Inject
    public TaskInputViewModel(TodoTaskRepository todoTaskRepository) {
        this.mTodoTaskRepository = todoTaskRepository;
    }

    public void createNewTodoTask() {
        todoTask.setValue(new TodoTask());
    }

    public void insertTodoTask() {
        this.mTodoTaskRepository.insertTodoTask(todoTask.getValue());
    }

    public void update() {
        this.mTodoTaskRepository.update(todoTask.getValue());
    }

    public void setFragmentTitle(String strTitle) {
        fragmentTitle.set(strTitle);
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
