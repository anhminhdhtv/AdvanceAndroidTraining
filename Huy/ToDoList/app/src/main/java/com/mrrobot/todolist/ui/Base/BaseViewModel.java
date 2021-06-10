package com.mrrobot.todolist.ui.Base;

import androidx.lifecycle.ViewModel;

import com.mrrobot.todolist.data.DataManager;

public class BaseViewModel<N> extends ViewModel {
    private final DataManager mDataManager;

    public BaseViewModel(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
