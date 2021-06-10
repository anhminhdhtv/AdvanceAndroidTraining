package com.mrrobot.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mrrobot.todolist.ui.Home.HomeFragment;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayoutContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        loadLayout(new HomeFragment());
    }

    public void loadLayout(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.constraintLayoutContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initLayout() {
        constraintLayoutContent = findViewById(R.id.constraintLayoutContent);
    }
}