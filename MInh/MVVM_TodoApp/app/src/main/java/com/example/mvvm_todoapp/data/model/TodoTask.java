package com.example.mvvm_todoapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.UUID;

import java.time.LocalDate;

@Entity(tableName = "task_table")
public class TodoTask {
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "task_name")
    private String mTaskName;

    @ColumnInfo(name = "date")
    private LocalDate mDate;

    @ColumnInfo(name = "description")
    private String mDescription;

    @Ignore
    public TodoTask(String taskName, LocalDate date, String description) {
        this.mTaskName = taskName;
        this.mDate = date;
        this.mDescription = description;
    }

    public TodoTask(){
        setId(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String mTaskName) {
        this.mTaskName = mTaskName;
    }

    public LocalDate getDate() {
        return mDate;
    }

    public void setDate(LocalDate mDate) {
        this.mDate = mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
