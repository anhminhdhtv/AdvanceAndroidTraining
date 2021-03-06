package com.example.mvvm_todoapp.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mvvm_todoapp.MyApplication;
import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.databinding.FragmentTaskDetailBinding;
import com.example.mvvm_todoapp.ui.MainActivity;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailFragment extends Fragment {

    @Inject
    public TaskDetailViewModel mTaskDetailViewModel;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mTaskID;


    public TaskDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TaskDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskDetailFragment newInstance(String id) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskID = getArguments().getString(ARG_PARAM1);
        }
        ((MyApplication) requireActivity().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task_detail, container, false);
        initViewHolder();
        initDataBinding(rootView);
        return rootView;
    }

    private void initViewHolder() {
        mTaskDetailViewModel.loadTodoTaskByID(mTaskID);
    }

    private void initDataBinding(View rootView) {
        FragmentTaskDetailBinding fragmentTaskDetailBinding = FragmentTaskDetailBinding.bind(rootView);
        fragmentTaskDetailBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentTaskDetailBinding.setViewModel(mTaskDetailViewModel);
        fragmentTaskDetailBinding.setListener(new TaskDetailListener() {
            @Override
            public void onEditClick(TodoTask todoTask) {
                ((MainActivity) requireActivity()).startEditTaskFragment(mTaskID);
            }

            @Override
            public void onDeleteClick(TodoTask todoTask) {
                mTaskDetailViewModel.deleteTask(mTaskID);
                requireFragmentManager().beginTransaction().remove(TaskDetailFragment.this).commit();
            }

            @Override
            public void onBackClick() {
                requireFragmentManager().beginTransaction().remove(TaskDetailFragment.this).commit();
            }
        });
    }
}