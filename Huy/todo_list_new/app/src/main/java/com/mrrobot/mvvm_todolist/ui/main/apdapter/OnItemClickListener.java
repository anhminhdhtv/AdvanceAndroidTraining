package com.mrrobot.mvvm_todolist.ui.main.apdapter;

import com.mrrobot.mvvm_todolist.data.model.Todo;

public interface OnItemClickListener {
    void onItemClick(Todo todoTask);
    void onItemEditClick(Todo todoTask);
    void onItemDeleteClick(Todo todoTask);
}
