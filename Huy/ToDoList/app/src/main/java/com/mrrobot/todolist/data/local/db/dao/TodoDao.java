package com.mrrobot.todolist.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mrrobot.todolist.data.model.db.Todo;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface TodoDao {
    @Delete
    public void delete(Todo... todos);

    @Insert
    public void insert(Todo... todos);

    @Update
    public void update(Todo... todos);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> loadAllTodo();

    @Query("DELETE FROM todo")
    void deleteAll();
}
