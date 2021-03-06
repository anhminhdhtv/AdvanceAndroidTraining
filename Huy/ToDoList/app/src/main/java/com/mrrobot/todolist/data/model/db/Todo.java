package com.mrrobot.todolist.data.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "todo")
public class Todo {
    @PrimaryKey
    @NonNull private String id;
    @ColumnInfo(name = "task_name")
    private String taskName;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "description")
    private String description;

    public Todo() {
    }

    public Todo(String id, String taskName, String date, String description) {
        this.id = id;
        this.taskName = taskName;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
