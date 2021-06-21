package com.mrrobot.mvvm_todolist.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.data.repository.Resource;

import javax.inject.Inject;

public class TaskDetailViewModel extends ViewModel {

    public LiveData<Todo> todo;
    private RepositoryData repositoryData;

    @Inject
    public TaskDetailViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    public void loadTodoTaskByID(String ID){
        if(todo != null){
            todo = null;
        }
        todo = loadTodoById(ID);
    }

    public LiveData<Todo> getTodo() {
        return todo;
    }

    public LiveData<Todo> loadTodoById(String id){
        LiveData<Resource<Todo>> data = repositoryData.getAllTodoWithID(id);
        return Transformations.switchMap(data, d -> {
            if (d == null) {
                return new MutableLiveData<>(null);
            } else {
                return new MutableLiveData<>(d.data);
            }
        });
    }

    public LiveData<Todo>  getTodoTask() {
        return todo;
    }

    public void deleteTodo(Todo todo){
        repositoryData.deleteTodoTask(todo.getId());
    }
}
