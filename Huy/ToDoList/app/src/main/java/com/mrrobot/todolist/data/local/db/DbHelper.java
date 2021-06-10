package com.mrrobot.todolist.data.local.db;

import android.database.Observable;

import com.mrrobot.todolist.data.model.db.Todo;

import java.util.List;

public interface DbHelper {
    Observable<List<Todo>> getAllTodo();
    Observable<Boolean> insertTodo(final Todo todo);

}
