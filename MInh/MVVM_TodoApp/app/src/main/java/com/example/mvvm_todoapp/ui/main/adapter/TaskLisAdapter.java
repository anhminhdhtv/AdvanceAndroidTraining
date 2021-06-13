package com.example.mvvm_todoapp.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.databinding.TodoTaskItemBinding;

import java.util.List;

public class TaskLisAdapter extends RecyclerView.Adapter<TaskLisAdapter.TaskViewHolder> {

    private List<TodoTask> mTodoTaskList;
    private OnItemClickListener mOnItemClickListener;


    public TaskLisAdapter(@Nullable OnItemClickListener onItemClickListener) {
        super();
        mOnItemClickListener = onItemClickListener;
        setHasStableIds(true);
    }

    public void setTodoTaskList(List<TodoTask> todoTaskList) {
        if (mTodoTaskList == null) {
            mTodoTaskList = todoTaskList;
        } else {
            mTodoTaskList.clear();
            mTodoTaskList.addAll(todoTaskList);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TodoTask todoTask = mTodoTaskList.get(position);
        holder.bind(todoTask, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mTodoTaskList == null ? 0 : mTodoTaskList.size();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_task_item, parent, false);
        TodoTaskItemBinding todoTaskItemBinding = DataBindingUtil.bind(itemView);
        return new TaskViewHolder(todoTaskItemBinding);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private TodoTaskItemBinding todoTaskItemBinding;

        public TaskViewHolder(TodoTaskItemBinding todoTaskItemBinding) {
            super(todoTaskItemBinding.getRoot());
            this.todoTaskItemBinding = todoTaskItemBinding;

        }

        public void bind(TodoTask todoTask, OnItemClickListener onItemClickListener) {
            todoTaskItemBinding.setTodoTask(todoTask);
            todoTaskItemBinding.setListen(onItemClickListener);
        }
    }
}
