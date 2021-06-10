package com.mrrobot.todolist.ui.splash;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrrobot.todolist.data.DataManager;
import com.mrrobot.todolist.ui.Base.BaseViewModel;

public class SplashViewModel extends ViewModel {
    private MutableLiveData<Boolean> timer;
    //get value current

    public MutableLiveData<Boolean> getTimer() {
        if(timer==null)
            timer = new MutableLiveData<>();
        return timer;
    }



}
