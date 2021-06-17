package com.mrrobot.mvvm_todolist.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mrrobot.mvvm_todolist.data.model.Todo;

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

    @Update
    void update(Todo todo);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getAllTask();

    @Query("SELECT * FROM todo WHERE id = :id")
    LiveData<Todo> getTaskByID(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasks(List<Todo> todoTasks);

}
