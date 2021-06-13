package com.example.mvvm_todoapp.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm_todoapp.data.model.TodoTask;

import java.util.List;

@Dao
public interface TodoTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TodoTask todoTask);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasks(TodoTask... todoTasks);

    @Query("DELETE FROM task_table WHERE id = :taskID")
    void deleteTask(int taskID);

    @Query("DELETE FROM task_table")
    void deleteAllTask();

    @Update
    void update(TodoTask todoTask);

    @Query("SELECT * FROM task_table")
    LiveData<List<TodoTask>> getAllTask();

    @Query("SELECT * FROM task_table WHERE id = :id")
    LiveData<TodoTask> getTaskByID(int id);
}