package com.mrrobot.mvvm_todolist.ui.main.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrrobot.mvvm_todolist.R;
import com.mrrobot.mvvm_todolist.data.model.Todo;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TodoViewHolder> {

    private List<Todo> mTodoList;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onClickDeleteItem(View v, int position);

        void onClickEditItem(View v, int position);
    }

    public TaskListAdapter(List<Todo> mTodoList, Context context, OnItemClickListener mListener) {
        this.mTodoList = mTodoList;
        this.context = context;
        this.mListener = mListener;
    }

    public void updateData(List<Todo> lstTodo) {
        this.mTodoList = lstTodo;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_item_todo, parent, false);
        return new TodoViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = mTodoList.get(position);
        holder.txtNameTodo.setText(todo.getTaskName());
        holder.txtDescription.setText(todo.getDescription());
        holder.txtDate.setText(todo.getDate());
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

        public TodoViewHolder(@NonNull View itemView, final TaskListAdapter.OnItemClickListener listener) {
            super(itemView);
            txtNameTodo = itemView.findViewById(R.id.tv_task_name);
            txtDate = itemView.findViewById(R.id.tv_date);
            txtDescription = itemView.findViewById(R.id.tv_description);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClickEditItem(v, position);
                        }
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClickDeleteItem(v, position);
                        }
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }

    }
}
