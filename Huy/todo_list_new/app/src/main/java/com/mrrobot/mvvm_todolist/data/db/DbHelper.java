package com.mrrobot.mvvm_todolist.data.db;

import android.database.Observable;

import com.mrrobot.mvvm_todolist.data.model.Todo;

import java.util.List;

public interface DbHelper {
    Observable<List<Todo>> getAllTodo();
    Observable<Boolean> insertTodo(final Todo todo);

}
