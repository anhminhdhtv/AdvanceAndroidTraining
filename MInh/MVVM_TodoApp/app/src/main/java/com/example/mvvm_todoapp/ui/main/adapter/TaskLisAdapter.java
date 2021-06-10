package com.example.mvvm_todoapp.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_todoapp.R;
import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.utils.Utilities;

import java.util.List;

public class TaskLisAdapter extends RecyclerView.Adapter<TaskLisAdapter.TaskViewHolder> {

    private List<TodoTask> mTodoTaskList;
    private OnItemClickListener mOnItemClickListener;


    public TaskLisAdapter(@Nullable OnItemClickListener onItemClickListener) {
        super();
        mOnItemClickListener = onItemClickListener;
        setHasStableIds(true);
    }

    public void setTodoTaskList(List<TodoTask> todoTaskList){
        if(mTodoTaskList == null){
            mTodoTaskList = todoTaskList;
        } else {
            mTodoTaskList.clear();
            mTodoTaskList.addAll(todoTaskList);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder( TaskViewHolder holder, int position) {
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
        return new TaskViewHolder(itemView);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvTaskName;
        private final TextView tvDate;
        private final TextView tvDescription;
        private final ImageButton btnDelete;
        private final ImageButton btnEdit;

        public TaskViewHolder( View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tv_task_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }

        public void bind(TodoTask todoTask, OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(null);
            tvTaskName.setText(todoTask.getTaskName());
            tvDate.setText(Utilities.convertDateToString(todoTask.getDate()));
            tvDescription.setText(todoTask.getDescription());
            itemView.setOnClickListener(v -> {
                onItemClickListener.onItemClick(todoTask);
            });

            btnDelete.setOnClickListener(v -> onItemClickListener.onItemDeleteClick(todoTask));
            btnEdit.setOnClickListener(v -> onItemClickListener.onItemEditClick(todoTask));
        }
    }
}
