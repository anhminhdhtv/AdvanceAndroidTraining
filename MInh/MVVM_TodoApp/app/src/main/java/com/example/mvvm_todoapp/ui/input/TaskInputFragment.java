package com.example.mvvm_todoapp.ui.input;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.db.converter.DateConverter;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.ui.viewModel.TodoTaskViewModel;
import com.example.mvvm_todoapp.utils.Utilities;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskInputFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mInputType;
    private int mTaskID;

    public static final int TYPE_CREATE = 0;
    public static final int TYPE_EDIT = 1;

    private EditText txtTaskName;
    private EditText txtTaskDescription;
    private TextView txtDate;
    private Button btnAction;
    private ImageButton btnCalendar;
    private TextView tvTitle;
    private ImageButton btnBack;

    private TodoTaskViewModel mTodoTaskViewModel;
    private TodoTask mTodoTask;

    public TaskInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TaskInputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskInputFragment newInstance(int inputType, int taskId) {
        TaskInputFragment fragment = new TaskInputFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, inputType);
        args.putInt(ARG_PARAM2, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mInputType = getArguments().getInt(ARG_PARAM1);
            mTaskID = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);
        initView(view);
        return view;
    }

    private void initView(View rootView){
        txtTaskName = rootView.findViewById(R.id.txt_task_name);
        txtTaskDescription = rootView.findViewById(R.id.txt_task_description);
        txtDate = rootView.findViewById(R.id.txt_date);
        btnAction = rootView.findViewById(R.id.btn_action);
        btnCalendar = rootView.findViewById(R.id.btn_date);
        tvTitle = rootView.findViewById(R.id.tv_title);
        btnBack = rootView.findViewById(R.id.btn_back);
        mTodoTaskViewModel = new ViewModelProvider(this).get(TodoTaskViewModel.class);

        if(mInputType == TYPE_CREATE){
            tvTitle.setText("Create new task");
            btnAction.setText("ADD");
            mTodoTask = new TodoTask();
        } else {
            tvTitle.setText("Edit task");
            btnAction.setText("UPDATE");

            subscribeToModel(mTodoTaskViewModel.getTodoTaskByID(mTaskID));
        }

        btnBack.setOnClickListener(v -> {
            requireFragmentManager().beginTransaction().remove(this).commit();
        });

        btnAction.setOnClickListener(v -> {
            mTodoTask.setTaskName(txtTaskName.getText().toString());
            try {
                mTodoTask.setDate(Utilities.convertStringToDate(txtDate.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mTodoTask.setDescription(txtTaskDescription.getText().toString());

            if(mInputType == TYPE_CREATE){
                mTodoTaskViewModel.insertTodoTask(mTodoTask);
            } else {
                mTodoTaskViewModel.update(mTodoTask);
            }

            requireFragmentManager().beginTransaction().remove(this).commit();
        });

        btnCalendar.setOnClickListener(v -> {
            MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            datePicker.addOnPositiveButtonClickListener(selection -> {
                txtDate.setText(Utilities.convertDateToString(DateConverter.toDate((Long)selection)));
            });
            datePicker.show(requireFragmentManager(),"");
        });

    }
    private void subscribeToModel(LiveData<TodoTask> liveData){
        liveData.observe(getViewLifecycleOwner(), todoTask -> {
            mTodoTask = todoTask;
            txtTaskName.setText(todoTask.getTaskName());
            txtDate.setText(Utilities.convertDateToString(todoTask.getDate()));
            txtTaskDescription.setText(todoTask.getDescription());
        });
    }
}