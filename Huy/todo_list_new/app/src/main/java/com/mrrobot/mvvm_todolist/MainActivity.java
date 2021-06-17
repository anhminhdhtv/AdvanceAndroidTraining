package com.mrrobot.mvvm_todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrrobot.mvvm_todolist.ui.detail.DetailFragment;
import com.mrrobot.mvvm_todolist.ui.input.InputFragment;
import com.mrrobot.mvvm_todolist.ui.input.InputViewModel;
import com.mrrobot.mvvm_todolist.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMainFragment();
    }

    private void startMainFragment(){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                new MainFragment(),
                MainFragment.class.getSimpleName()
        ).commit();
    }

    public void startDetailFragment(String id){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                DetailFragment.newInstance(id),
                MainFragment.class.getSimpleName()
        ).commit();
    }

    public void startInputFragment(String id, int typeAction){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                InputFragment.newInstance(id,typeAction),
                MainFragment.class.getSimpleName()
        ).commit();
    }
    public void startAddFragment(int typeAction){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                InputFragment.newInstance("",typeAction),
                MainFragment.class.getSimpleName()
        ).commit();
    }
}