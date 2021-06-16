package com.example.mvvm_todoapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.ui.detail.TaskDetailFragment;
import com.example.mvvm_todoapp.ui.input.TaskInputFragment;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMainFragment();
    }

    private void startMainFragment(){
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new MainFragment(), MainFragment.class.getSimpleName()).commit();
    }

    public void startDetailFragment(String taskID){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                TaskDetailFragment.newInstance(taskID),
                TaskInputFragment.class.getSimpleName()
        ).commit();
    }

    public void startCreateTaskFragment(){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                TaskInputFragment.newInstance(TaskInputFragment.TYPE_CREATE, ""),
                TaskInputFragment.class.getSimpleName()
        ).commit();
    }

    public void startEditTaskFragment(String ID){
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_container,
                TaskInputFragment.newInstance(TaskInputFragment.TYPE_EDIT, ID),
                TaskInputFragment.class.getSimpleName()
        ).commit();
    }
}