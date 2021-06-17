package com.mrrobot.mvvm_todolist.ui.detail;


import com.mrrobot.mvvm_todolist.data.model.Todo;

public interface TaskDetailListener {
    void onEditClick(Todo todoTask);
    void onDeleteClick(Todo todoTask);
    void onBackClick();
}
