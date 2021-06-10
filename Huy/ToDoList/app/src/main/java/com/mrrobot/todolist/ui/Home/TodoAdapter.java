package com.mrrobot.todolist.ui.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrrobot.todolist.R;
import com.mrrobot.todolist.data.model.db.Todo;
import com.mrrobot.todolist.utils.AppConstants;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    List<Todo> todoArrayList;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onClickDeleteItem(View v,int position);
        void onClickEditItem(View v,int position);
    }
    public  void setOnItemClickListener(TodoAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public TodoAdapter(List<Todo> lstTodo, Context context,OnItemClickListener listener) {
        this.todoArrayList = lstTodo;
        this.context = context;
        this.mListener = listener;
    }

    public void updateData(List<Todo> lstTodo){
        this.todoArrayList = lstTodo;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_todo,parent,false);
        return new TodoViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoArrayList.get(position);
        holder.txtNameTodo.setText(todo.getTaskName());
        holder.txtDate.setText("Date: "+todo.getDate());
        holder.txtDescription.setText(todo.getDescription());
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder{
        TextView txtNameTodo,txtDate,txtDescription;
        ImageButton imgEdit,imgDelete;

        public TodoViewHolder(@NonNull View itemView,final TodoAdapter.OnItemClickListener listener) {
            super(itemView);
            txtNameTodo = itemView.findViewById(R.id.textViewTodoName);
            txtDate = itemView.findViewById(R.id.textViewDate);
            txtDescription = itemView.findViewById(R.id.textViewDescription);
            imgDelete = itemView.findViewById(R.id.imageButtonDelete);
            imgEdit = itemView.findViewById(R.id.imageButtonEdit);

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onClickEditItem(v,position);
                        }
                    }
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onClickDeleteItem(v,position);
                        }
                    }
                }
            });

            itemView.setOnClickListener(v -> {
                if(listener != null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(v,position);
                    }
                }
            });
        }
    }
}
