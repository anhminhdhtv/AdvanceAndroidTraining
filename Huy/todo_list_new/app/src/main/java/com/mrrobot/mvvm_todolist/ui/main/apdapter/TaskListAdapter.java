package com.mrrobot.mvvm_todolist.ui.main.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.databinding.CustomItemTodoBinding;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TodoViewHolder> {

    private List<Todo> mTodoList;
    Context context;
    private OnItemClickListener mListener;


    public TaskListAdapter( OnItemClickListener mListener) {
        super();
        this.mListener = mListener;
        setHasStableIds(true);
    }

    public void updateData(List<Todo> lstTodo) {
        if(mTodoList==null){
            this.mTodoList = lstTodo;
        }
        else {
            this.mTodoList.clear();
            this.mTodoList.addAll(lstTodo);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_item_todo, parent, false);
        CustomItemTodoBinding customItemTodoBinding = DataBindingUtil.bind(itemView);
        return new TodoViewHolder(customItemTodoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = mTodoList.get(position);
        holder.bind(todo,mListener);
    }


    @Override
    public int getItemCount() {
        if (mTodoList!=null)
            return mTodoList.size();
        return 0;
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameTodo, txtDate, txtDescription;
        ImageButton btnEdit, btnDelete;
        private CustomItemTodoBinding todoBinding;
        public TodoViewHolder(CustomItemTodoBinding customItemTodoBinding) {
            super(customItemTodoBinding.getRoot());
            this.todoBinding = customItemTodoBinding;
        }
        public void bind(Todo todo, OnItemClickListener onItemClickListener){
            this.todoBinding.setTodoTask(todo);
            this.todoBinding.setListen(onItemClickListener);
        }

    }
}
