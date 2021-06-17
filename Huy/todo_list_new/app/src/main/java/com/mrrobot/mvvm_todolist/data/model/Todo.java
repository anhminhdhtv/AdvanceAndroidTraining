package com.mrrobot.mvvm_todolist.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "todo")
public class Todo implements Serializable {
    @Expose
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private String id;

    @Expose
    @SerializedName("task_name")
    @ColumnInfo(name = "task_name")
    private String taskName;

    @Expose
    @SerializedName("date")
    @ColumnInfo(name = "date")
    private String date;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;


    @Ignore
    public Todo(String id, String taskName, String date, String description) {
        this.id = id;
        this.taskName = taskName;
        this.date = date;
        this.description = description;
    }
    public Todo(){
        setId(UUID.randomUUID().toString());
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
