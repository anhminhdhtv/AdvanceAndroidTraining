package com.mrrobot.todolist.ui.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mrrobot.todolist.MainActivity;
import com.mrrobot.todolist.R;
import com.mrrobot.todolist.data.model.db.Todo;
import com.mrrobot.todolist.ui.EditTask.EditFragment;
import com.mrrobot.todolist.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    List<Todo> todoArrayList;
    TodoAdapter todoAdapter;
    RecyclerView recyclerViewTodoList;
    FloatingActionButton fltButtonAddTask;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        //mViewModel.insertTodo(new Todo("01","name","noww","dfsaf"));

        recyclerViewTodoList = view.findViewById(R.id.recycleTodoList);
        fltButtonAddTask = view.findViewById(R.id.buttonAddTask);
        todoArrayList = new ArrayList<>();

        recyclerViewTodoList.setHasFixedSize(true);
        recyclerViewTodoList.setItemViewCacheSize(10);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewTodoList.setLayoutManager(layoutManager);
        mViewModel.getListTodo().observe(requireActivity(), todos -> {
            todoAdapter.updateData(todos);
            todoArrayList = mViewModel.getListTodo().getValue();
        });

        todoAdapter = new TodoAdapter(todoArrayList, getContext(), new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mViewModel.selectItem(todoArrayList.get(position));
                mViewModel.setTypeAction(AppConstants.TYPE_SHOW_DETAIL);

                loadLayout(new EditFragment());
            }

            @Override
            public void onClickDeleteItem(View v, int position) {
                //Toast.makeText(getContext(), "Bạn sẽ DELETE " + todoArrayList.get(position).getTaskName(), Toast.LENGTH_SHORT).show();
                mViewModel.deleteTodo(todoArrayList.get(position)).observe(requireActivity(), new Observer<List<Todo>>() {
                    @Override
                    public void onChanged(List<Todo> todos) {
                        todoAdapter.updateData(todos);
                        todoArrayList = mViewModel.getListTodo().getValue();
                    }
                });
                //todoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onClickEditItem(View v, int position) {
                //Toast.makeText(getContext(), "Bạn sẽ EDIT " + todoArrayList.get(position).getTaskName(), Toast.LENGTH_SHORT).show();

                mViewModel.selectItem(todoArrayList.get(position));
                mViewModel.setTypeAction(AppConstants.TYPE_EDIT);

                loadLayout(new EditFragment());
            }
        });
        recyclerViewTodoList.setAdapter(todoAdapter);
        todoAdapter.notifyDataSetChanged();
        fltButtonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.selectItem(null);
                mViewModel.setTypeAction(AppConstants.TYPE_CREATE);

                loadLayout(new EditFragment());
            }
        });

    }
    public void loadLayout(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.constraintLayoutContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}