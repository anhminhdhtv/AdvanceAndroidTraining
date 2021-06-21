package com.mrrobot.mvvm_todolist.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mrrobot.mvvm_todolist.MainActivity;
import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.databinding.FragmentDetailBinding;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mTaskID;
    @Inject
    TaskDetailViewModel taskDetailViewModel;
    Todo todoSelected;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String id) {
        DetailFragment fragment = new DetailFragment();
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

//        AppContainer appContainer = ((MyApplication) requireActivity().getApplicationContext()).appContainer;
//        taskDetailViewModel = new TaskDetailViewModel(appContainer.repositoryData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(), "Hello " + mTaskID, Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initDataBinding(view);
        return view;
    }

    private void initDataBinding(View view) {
        FragmentDetailBinding fragmentDetailBinding = FragmentDetailBinding.bind(view);
        fragmentDetailBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentDetailBinding.setViewModel(taskDetailViewModel);
        taskDetailViewModel.loadTodoTaskByID(mTaskID);
        fragmentDetailBinding.setListener(new TaskDetailListener() {
            @Override
            public void onEditClick(String todoTask) {
                Toast.makeText(getContext(), "Hello onEditClick", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).startInputFragment(todoTask, 1);
            }

            @Override
            public void onDeleteClick(Todo todoTask) {
                Toast.makeText(getContext(), "Hello onDeleteClick", Toast.LENGTH_SHORT).show();
                taskDetailViewModel.deleteTodo(todoSelected);
                requireFragmentManager().beginTransaction().remove(DetailFragment.this).commit();
            }

            @Override
            public void onBackClick() {
                requireFragmentManager().beginTransaction().remove(DetailFragment.this).commit();
            }
        });
    }

//    private void initAction() {
//        imgCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requireFragmentManager().beginTransaction().remove(DetailFragment.this).commit();
//            }
//        });
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requireFragmentManager().beginTransaction().remove(DetailFragment.this).commit();
//                taskDetailViewModel.deleteTodo(todoSelected);
//            }
//        });
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity) requireActivity()).startInputFragment(mTaskID,1);
//            }
//        });
//    }


//    private void initLayout(View view) {
//        //taskDetailViewModel = new  ViewModelProvider(requireActivity()).get(TaskDetailViewModel.class);
//        txtDate = view.findViewById(R.id.textViewDate);
//        txtDescription = view.findViewById(R.id.textViewDescription);
//        txtTaskName = view.findViewById(R.id.textViewNameTask);
//        btnDelete = view.findViewById(R.id.buttonDelete);
//        btnEdit = view.findViewById(R.id.buttonEdit);
//        imgCancel = view.findViewById(R.id.btn_back);
//    }
}