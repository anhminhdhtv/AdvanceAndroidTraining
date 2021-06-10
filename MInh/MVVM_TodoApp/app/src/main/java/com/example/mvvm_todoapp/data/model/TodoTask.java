package com.example.mvvm_todoapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class TodoTask {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id = 0;

    @ColumnInfo(name = "task_name")
    private String mTaskName;

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "description")
    private String mDescription;

    public TodoTask(String taskName, Date date, String description) {
        this.mTaskName = taskName;
        this.mDate = date;
        this.mDescription = description;
    }

    public TodoTask(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String mTaskName) {
        this.mTaskName = mTaskName;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
