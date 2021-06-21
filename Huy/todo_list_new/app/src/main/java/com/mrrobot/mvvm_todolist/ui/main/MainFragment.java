package com.mrrobot.mvvm_todolist.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.mrrobot.mvvm_todolist.MainActivity;
import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.databinding.FragmentMainBinding;
import com.mrrobot.mvvm_todolist.ui.main.apdapter.MainViewModel;
import com.mrrobot.mvvm_todolist.ui.main.apdapter.OnItemClickListener;
import com.mrrobot.mvvm_todolist.ui.main.apdapter.TaskListAdapter;

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

    List<Todo> mTodoList;
    TaskListAdapter taskListAdapter;
    RecyclerView recyclerViewTaskLis;
    @Inject
    MainViewModel mainViewModel;
    FragmentMainBinding fragmentMainBinding;
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
//        AppContainer appContainer = ((MyApplication) requireActivity().getApplicationContext()).appContainer;
//        mainViewModel = new MainViewModel(appContainer.repositoryData);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initLayout(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeToModel(mainViewModel.getAllTodoList());
    }

    private void initLayout(View view) {
        fragmentMainBinding = DataBindingUtil.bind(view);
        fragmentMainBinding.btnAdd.setOnClickListener(v -> ((MainActivity) requireActivity()).startAddFragment(0));
        taskListAdapter = new TaskListAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(Todo todoTask) {
                ((MainActivity) requireActivity()).startDetailFragment(todoTask.getId());
            }

            @Override
            public void onItemEditClick(Todo todoTask) {
                ((MainActivity) requireActivity()).startInputFragment(todoTask.getId(),1);
            }

            @Override
            public void onItemDeleteClick(Todo todoTask) {
                mainViewModel.deleteTodo(todoTask);
            }
        });

        fragmentMainBinding.recycleViewListTodo.setAdapter(taskListAdapter);
//        mainViewModel.getAllTodoList().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
//            @Override
//            public void onChanged(List<Todo> todos) {
//                taskListAdapter.updateData(todos);
//            }
//        });
    }
    private void subscribeToModel(LiveData<List<Todo>> liveData) {
        liveData.observe(getViewLifecycleOwner(), todoTasks -> {
            taskListAdapter.updateData(todoTasks);
        });
    }
}