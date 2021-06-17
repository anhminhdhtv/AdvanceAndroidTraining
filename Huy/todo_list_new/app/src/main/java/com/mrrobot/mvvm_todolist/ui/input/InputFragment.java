package com.mrrobot.mvvm_todolist.ui.input;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.ui.detail.DetailFragment;
import com.mrrobot.mvvm_todolist.ui.detail.TaskDetailViewModel;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mTaskID;
    private int mTypeAction;
    Button btnAction;
    Todo todoSelected;
    EditText edtTaskName,edtDate,edtDescription;
    ImageButton imgCancel;
    InputViewModel inputViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(String id,int typeAction) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        args.putInt(ARG_PARAM2, typeAction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskID = getArguments().getString(ARG_PARAM1);
            mTypeAction = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        initLayout(view);
        initData();
        initAction();
        return view;
    }

    private void initAction() {
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireFragmentManager().beginTransaction().remove(InputFragment.this).commit();
            }
        });

        btnAction.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(mTypeAction==0){
                    Todo todo = new Todo(LocalDateTime.now().toString(),edtTaskName.getText().toString(),edtDate.getText().toString(),edtDescription.getText().toString());
                    inputViewModel.insertTodo(todo);
                    requireFragmentManager().beginTransaction().remove(InputFragment.this).commit();
                }else {
                    Todo todo = new Todo(todoSelected.getId(),edtTaskName.getText().toString(),edtDate.getText().toString(),edtDescription.getText().toString());
                    inputViewModel.updateTodo(todo);
                }
                requireFragmentManager().beginTransaction().remove(InputFragment.this).commit();
            }
        });
    }

    private void initData() {
        inputViewModel.loadTodoById(mTaskID).observe(requireActivity(), new Observer<Todo>() {
            @Override
            public void onChanged(Todo todo) {
                todoSelected = todo;
                if(todoSelected!=null){
                    edtTaskName.setText(todoSelected.getTaskName());
                    edtDate.setText(todoSelected.getDate());
                    edtDescription.setText(todoSelected.getDescription());
                }
            }
        });
    }

    private void initLayout(View view) {
        inputViewModel = new ViewModelProvider(requireActivity()).get(InputViewModel.class);
        edtTaskName = view.findViewById(R.id.editTextTaskName);
        edtDate = view.findViewById(R.id.editTextDate);
        edtDescription = view.findViewById(R.id.editTextDescription);
        btnAction = view.findViewById(R.id.buttonAction);
        imgCancel = view.findViewById(R.id.btn_back_from_edit);
        if(mTypeAction==0){
            btnAction.setText("Create");
        } else {
            btnAction.setText("Edit");
        }
    }
}