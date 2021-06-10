package com.example.mvvm_todoapp.ui.main.adapter;

import com.example.mvvm_todoapp.data.model.TodoTask;

public interface OnItemClickListener{
    void onItemClick(TodoTask todoTask);
    void onItemEditClick(TodoTask todoTask);
    void onItemDeleteClick(TodoTask todoTask);
}
