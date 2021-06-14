package com.example.mvvm_todoapp.ui.detail;

import com.example.mvvm_todoapp.data.model.TodoTask;

public interface TaskDetailListener {
    void onEditClick(TodoTask todoTask);
    void onDeleteClick(TodoTask todoTask);
    void onBackClick();
}
