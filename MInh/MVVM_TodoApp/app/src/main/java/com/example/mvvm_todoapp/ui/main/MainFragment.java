package com.example.mvvm_todoapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.MyApplication;
import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.databinding.FragmentMainBinding;
import com.example.mvvm_todoapp.ui.MainActivity;
import com.example.mvvm_todoapp.ui.main.adapter.OnItemClickListener;
import com.example.mvvm_todoapp.ui.main.adapter.TaskLisAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Inject
    public MainTodoTaskViewModel mMainTodoTaskViewModel;
    private FragmentMainBinding fragmentMainBinding;
    private TaskLisAdapter mTaskLisAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ((MyApplication)requireActivity().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        fragmentMainBinding = DataBindingUtil.bind(rootView);
        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeToModel(mMainTodoTaskViewModel.getAllTodoTasks());
    }

    private void initView() {

        fragmentMainBinding.btnAdd.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).startCreateTaskFragment();
        });
        mTaskLisAdapter = new TaskLisAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(TodoTask todoTask) {
                ((MainActivity) requireActivity()).startDetailFragment(todoTask.getId());
            }

            @Override
            public void onItemEditClick(TodoTask todoTask) {
                ((MainActivity) requireActivity()).startEditTaskFragment(todoTask.getId());
            }

            @Override
            public void onItemDeleteClick(TodoTask todoTask) {
                mMainTodoTaskViewModel.deleteTask(todoTask.getId());
            }
        });
        fragmentMainBinding.listTask.setAdapter(mTaskLisAdapter);
    }

    private void subscribeToModel(LiveData<List<TodoTask>> liveData) {
        liveData.observe(getViewLifecycleOwner(), todoTasks -> {
            mTaskLisAdapter.setTodoTaskList(todoTasks);
        });
    }
}