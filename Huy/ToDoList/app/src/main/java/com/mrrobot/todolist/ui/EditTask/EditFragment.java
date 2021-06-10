package com.mrrobot.todolist.ui.EditTask;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrrobot.todolist.R;
import com.mrrobot.todolist.data.model.db.Todo;
import com.mrrobot.todolist.ui.Home.HomeViewModel;
import com.mrrobot.todolist.utils.AppConstants;

import java.util.List;
import java.util.Timer;

public class EditFragment extends Fragment {

    public static EditFragment newInstance() {
        return new EditFragment();
    }
    HomeViewModel mViewModel;
    EditText edtTaskName,edtDate,edtDescription;
    TextView txtTitle;
    Button btnAction,btnEdit,btnDelete;
    LinearLayout lnViewDetail;
    Todo todoCurrent;
    int typeAction;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        initlayout(view);
        initData();
        action();
        return view;
    }

    private void action() {
        btnAction.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(typeAction==AppConstants.TYPE_EDIT){
                    Todo newTodo = new Todo(todoCurrent.getId(),edtTaskName.getText().toString(),edtDate.getText().toString(),edtDescription.getText().toString());
                    mViewModel.updateTodo(newTodo);
                } else if(typeAction==AppConstants.TYPE_CREATE){
                    btnDelete.setBackgroundColor(getResources().getColor(R.color.color_delete));
                    btnEdit.setBackgroundColor(getResources().getColor(R.color.color_edit));
                    mViewModel.insertTodo(new Todo(java.time.LocalDateTime.now().toString(),edtTaskName.getText().toString(),edtDate.getText().toString(),edtDescription.getText().toString()));
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deleteTodo(todoCurrent);
                edtDescription.setText("");
                edtDate.setText("");
                edtTaskName.setText("");
                btnDelete.setEnabled(false);

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo newTodo = new Todo(todoCurrent.getId(),edtTaskName.getText().toString(),edtDate.getText().toString(),edtDescription.getText().toString());
                mViewModel.updateTodo(newTodo);
            }
        });
    }

    private void initlayout(View view) {
        edtTaskName = view.findViewById(R.id.editTextTaskName);
        edtDate = view.findViewById(R.id.editTextDate);
        edtDescription = view.findViewById(R.id.editTextDescription);
        btnAction = view.findViewById(R.id.buttonAction);
        lnViewDetail = view.findViewById(R.id.viewDetail);
        btnDelete = view.findViewById(R.id.buttonDelete);
        btnEdit = view.findViewById(R.id.buttonEdit);
        txtTitle = view.findViewById(R.id.textViewTitle);
    }

    private void initData() {

        mViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<Todo>() {
            @Override
            public void onChanged(Todo todo) {
                if(todo!=null) {
                    todoCurrent = todo;
                    edtTaskName.setText(todo.getTaskName());
                    edtDate.setText(todo.getDate());
                    edtDescription.setText(todo.getDescription());
                }
                else {
                    edtTaskName.setText("");
                    edtDate.setText("");
                    edtDescription.setText("");
                }

            }
        });
        mViewModel.getTypeAction().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                typeAction = integer;
                if(integer== AppConstants.TYPE_EDIT) {
                    btnAction.setVisibility(View.VISIBLE);
                    lnViewDetail.setVisibility(View.GONE);
                    btnAction.setText("Edit");
                    btnAction.setBackgroundColor(getResources().getColor(R.color.color_edit));
                }
                if(integer== AppConstants.TYPE_SHOW_DETAIL) {
                    txtTitle.setText("Detail Task");
                    btnAction.setVisibility(View.GONE);
                    lnViewDetail.setVisibility(View.VISIBLE);
                    btnAction.setBackgroundColor(getResources().getColor(R.color.color_edit));
                }
                if(integer== AppConstants.TYPE_CREATE) {

                    txtTitle.setText("Create new Task");
                    btnAction.setVisibility(View.VISIBLE);
                    lnViewDetail.setVisibility(View.GONE);
                    btnAction.setText("CREATE");
                    btnAction.setBackgroundColor(getResources().getColor(R.color.color_edit));
                }
            }
        });
    }


}