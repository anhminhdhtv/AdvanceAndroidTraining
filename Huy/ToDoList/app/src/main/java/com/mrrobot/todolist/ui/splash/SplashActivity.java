package com.mrrobot.todolist.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.mrrobot.todolist.MainActivity;
import com.mrrobot.todolist.R;
import com.mrrobot.todolist.data.local.db.AppDatabase;
import com.mrrobot.todolist.data.local.db.dao.TodoDao;
import com.mrrobot.todolist.data.model.db.Todo;

public class SplashActivity extends AppCompatActivity {

    SplashViewModel splashViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        init();
        countDownTimeShowSplash();
//        goToMainActivity();

        //trans to Main Activity
    }

    private void goToMainActivity() {
        if(splashViewModel.getTimer().getValue()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void init(){
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void countDownTimeShowSplash(){
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                splashViewModel.getTimer().setValue(true);
                goToMainActivity();
            }
        }.start();

    }
}