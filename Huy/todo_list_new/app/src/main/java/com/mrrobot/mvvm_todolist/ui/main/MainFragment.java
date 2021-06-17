package com.mrrobot.mvvm_todolist.ui.main;

import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mrrobot.mvvm_todolist.MainActivity;
import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;
import com.mrrobot.mvvm_todolist.databinding.FragmentMainBinding;
import com.mrrobot.mvvm_todolist.ui.main.apdapter.MainViewModel;
import com.mrrobot.mvvm_todolist.ui.main.apdapter.OnItemClickListener;
import com.mrrobot.mvvm_todolist.ui.main.apdapter.TaskListAdapter;
import com.mrrobot.mvvm_todolist.utils.AppContainer;

import java.util.ArrayList;
import java.util.List;

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
    MainViewModel mainViewModel;
    FloatingActionButton btnAdd;
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
        AppContainer appContainer = ((MyApplication) requireActivity().getApplicationContext()).appContainer;
        mainViewModel = new MainViewModel(appContainer.repositoryData);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        FragmentMainBinding fragmentMainBinding = DataBindingUtil.bind(view);
        fragmentMainBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).startAddFragment(0);
            }
        });
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
        mainViewModel.getAllTodoList().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                taskListAdapter.updateData(todos);
            }
        });

//        //mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
//        mTodoList  = new ArrayList<>();
//        recyclerViewTaskLis = view.findViewById(R.id.recycleViewListTodo);
//        btnAdd = view.findViewById(R.id.btn_add);
//        recyclerViewTaskLis.setHasFixedSize(true);
//        recyclerViewTaskLis.setItemViewCacheSize(10);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        recyclerViewTaskLis.setLayoutManager(layoutManager);
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        mainViewModel.getAllTodoList().observe(requireActivity(), new Observer<List<Todo>>() {
//            @Override
//            public void onChanged(List<Todo> todos) {
//                taskListAdapter.updateData(todos);
//                mTodoList = mainViewModel.getAllTodoList().getValue();
//            }
//        });
//        taskListAdapter = new TaskListAdapter(mTodoList, getContext(), new TaskListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Toast.makeText(getContext(), "Xem chi tiết", Toast.LENGTH_SHORT).show();
//                ((MainActivity) requireActivity()).startDetailFragment(mTodoList.get(position).getId());
//            }
//
//            @Override
//            public void onClickDeleteItem(View v, int position) {
//                Toast.makeText(getContext(), "Xóa todo", Toast.LENGTH_SHORT).show();
//                mainViewModel.deleteTodo(mTodoList.get(position));
//            }
//
//            @Override
//            public void onClickEditItem(View v, int position) {
//                Toast.makeText(getContext(), "Sửa todo", Toast.LENGTH_SHORT).show();
//                ((MainActivity)requireActivity()).startInputFragment(mTodoList.get(position).getId(),1);
//            }
//        });
//        recyclerViewTaskLis.setAdapter(taskListAdapter);
//        taskListAdapter.notifyDataSetChanged();

    }
}