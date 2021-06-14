package com.example.mvvm_todoapp.ui.input.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvm_todoapp.BR;

public class InputTaskData extends BaseObservable {
    private String taskName;
    private String taskDescription;
    private String date;

    @Bindable
    public String getFragmentTitle() {
        return fragmentTitle;
    }

    public void setFragmentTitle(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
        notifyPropertyChanged(BR.fragmentTitle);

    }

    private String fragmentTitle;

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
        notifyPropertyChanged(BR.taskDescription);
    }


    @Bindable
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
        notifyPropertyChanged(BR.taskName);
    }
}
