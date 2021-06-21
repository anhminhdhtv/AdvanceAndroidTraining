package com.mrrobot.mvvm_todolist.ui.input;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.data.repository.Resource;

import javax.inject.Inject;

public class InputViewModel extends ViewModel {
    public LiveData<Todo> todo;
    private final RepositoryData repositoryData;
    public MutableLiveData<Todo> todoTask = new MutableLiveData<>(new Todo());

    @Inject
    public InputViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }


    public LiveData<Todo> getTodoTask() {
        return todo;
    }

    public void update() {
        AppDatabase.databaseWriteExecutor.execute(() -> repositoryData.updateTodo(todoTask.getValue()));

    }

    public LiveData<Todo> getTodoByID(String ID) {
        LiveData<Resource<Todo>> data = repositoryData.getAllTodoWithID(ID);
        return Transformations.switchMap(data, d -> {
            if (d == null) {
                return new MutableLiveData<>(null);
            } else {
                return new MutableLiveData<>(d.data);
            }
        });

    }

    public void createNewTodoTask() {
        todoTask.setValue(new Todo());
    }


    public void insertTodo() {
        repositoryData.insertTodo(todoTask.getValue());
    }
}
