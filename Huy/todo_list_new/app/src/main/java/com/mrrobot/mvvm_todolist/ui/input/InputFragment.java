package com.mrrobot.mvvm_todolist.ui.input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.databinding.FragmentInputBinding;

import javax.inject.Inject;

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
    @Inject
    InputViewModel inputViewModel;

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
    public static InputFragment newInstance(String id, int typeAction) {
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
        ((MyApplication) requireActivity().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        initDataBinding(view);
        return view;
    }

    private void initDataBinding(View view) {
        FragmentInputBinding fragmentInputBinding = FragmentInputBinding.bind(view);
        fragmentInputBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentInputBinding.setViewModel(inputViewModel);
        fragmentInputBinding.setTodo(inputViewModel.todoTask);
        if (mTypeAction == 0) {
            inputViewModel.createNewTodoTask();
        } else {
            inputViewModel.getTodoByID(mTaskID).observe(getViewLifecycleOwner(), todo -> inputViewModel.todoTask.setValue(todo));
        }
        fragmentInputBinding.setListener(new TaskInputListener() {
            @Override
            public void onActionClick() {
                if (mTypeAction == 0) {
                    inputViewModel.insertTodo();
                } else {
                    inputViewModel.update();
                }
                requireFragmentManager().beginTransaction().remove(InputFragment.this).commit();
            }

            @Override
            public void onBackClick() {

                requireFragmentManager().beginTransaction().remove(InputFragment.this).commit();
            }
        });
    }
}