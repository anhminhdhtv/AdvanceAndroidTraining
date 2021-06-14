package com.example.mvvm_todoapp.ui.input;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.databinding.FragmentTaskInputBinding;
import com.example.mvvm_todoapp.ui.base.ViewModelFactory;

import java.time.LocalDate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskInputFragment extends Fragment {

    public static final int TYPE_CREATE = 0;
    public static final int TYPE_EDIT = 1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private int mInputType;
    private String mTaskID;
    private TaskInputViewModel mTaskInputViewModel;

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
    public static TaskInputFragment newInstance(int inputType, String taskId) {
        TaskInputFragment fragment = new TaskInputFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, inputType);
        args.putString(ARG_PARAM2, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mInputType = getArguments().getInt(ARG_PARAM1);
            mTaskID = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);
        initView();

        initDataBinding(view);
        return view;
    }

    private void initView() {
        ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity().getApplication());
        mTaskInputViewModel = new ViewModelProvider(requireActivity(), factory).get(TaskInputViewModel.class);

        if (mInputType == TYPE_CREATE) {
            mTaskInputViewModel.setFragmentTitle("Create new task");
            mTaskInputViewModel.createNewTodoTask();
        } else {
            mTaskInputViewModel.setFragmentTitle("Edit task");
            mTaskInputViewModel.getTodoTaskById(mTaskID).observe(getViewLifecycleOwner(), todoTask -> {
                mTaskInputViewModel.todoTask.setValue(todoTask);
            });
        }

    }

    private void initDataBinding(View rootView) {
        FragmentTaskInputBinding fragmentTaskInputBinding = FragmentTaskInputBinding.bind(rootView);
        fragmentTaskInputBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentTaskInputBinding.setTodoTask(mTaskInputViewModel.todoTask);
        fragmentTaskInputBinding.setTitle(mTaskInputViewModel.fragmentTitle);
        fragmentTaskInputBinding.setListener(new TaskInputListener() {
            @Override
            public void onActionClick() {
                if (mInputType == TYPE_CREATE) {
                    mTaskInputViewModel.insertTodoTask();
                } else {
                    mTaskInputViewModel.update();
                }
                requireFragmentManager().beginTransaction().remove(TaskInputFragment.this).commit();
            }

            @Override
            public void onCalenderButtonClick() {
                DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
                    TodoTask todoTask = mTaskInputViewModel.todoTask.getValue();
                    todoTask.setDate(LocalDate.of(year, monthOfYear, dayOfMonth));
                    mTaskInputViewModel.todoTask.setValue(todoTask);
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        dateSetListener, LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

                datePickerDialog.show();
            }

            @Override
            public void onBackClick() {
                requireFragmentManager().beginTransaction().remove(TaskInputFragment.this).commit();
            }
        });
    }

}