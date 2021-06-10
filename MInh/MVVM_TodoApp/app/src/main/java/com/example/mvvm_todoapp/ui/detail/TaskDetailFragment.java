package com.example.mvvm_todoapp.ui.detail;

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
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.ui.main.MainActivity;
import com.example.mvvm_todoapp.ui.viewModel.TodoTaskViewModel;
import com.example.mvvm_todoapp.utils.Utilities;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mTaskID;
    private TextView txtTaskName;
    private TextView txtTaskDescription;
    private TextView txtDate;
    private ImageButton btnBack;
    private Button btnEdit;
    private Button btnDelete;

    private TodoTaskViewModel mTodoTaskViewModel;


    public TaskDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TaskDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskDetailFragment newInstance(int id) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskID = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_task_detail, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView){
        txtTaskName = rootView.findViewById(R.id.tv_task_name);
        txtTaskDescription = rootView.findViewById(R.id.tv_description);
        txtDate = rootView.findViewById(R.id.tv_date);
        btnDelete = rootView.findViewById(R.id.btn_delete);
        btnEdit = rootView.findViewById(R.id.btn_edit);
        btnBack = rootView.findViewById(R.id.btn_back);
        mTodoTaskViewModel = new ViewModelProvider(this).get(TodoTaskViewModel.class);

        subscribeToModel(mTodoTaskViewModel.getTodoTaskByID(mTaskID));


        btnBack.setOnClickListener(v -> {
            requireFragmentManager().beginTransaction().remove(this).commit();
        });

        btnEdit.setOnClickListener(v -> {
            ((MainActivity)requireActivity()).startEditTaskFragment(mTaskID);
        });

        btnDelete.setOnClickListener(v -> {
            mTodoTaskViewModel.deleteTask(mTaskID);
            requireFragmentManager().beginTransaction().remove(this).commit();
        });


    }
    private void subscribeToModel(LiveData<TodoTask> liveData){
        liveData.observe(getViewLifecycleOwner(), todoTask -> {
            txtTaskName.setText(todoTask.getTaskName() == null? "":todoTask.getTaskName());
            txtDate.setText(Utilities.convertDateToString(todoTask.getDate()));
            txtTaskDescription.setText(todoTask.getDescription());
        });
    }
}