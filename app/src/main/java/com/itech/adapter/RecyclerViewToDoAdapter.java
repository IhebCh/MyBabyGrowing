package com.itech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.itech.models.ToDo;
import com.itech.mybabygrowing.R;

import java.util.List;

/**
 * Created by oSunshine on 06/07/2015.
 */
public class RecyclerViewToDoAdapter extends RecyclerView.Adapter<RecyclerViewToDoAdapter.RecyclerViewTodoHolder>{

    private final LayoutInflater inflater;
    private final List<ToDo> data;

    public RecyclerViewToDoAdapter(Context context, List<ToDo> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewTodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.todo_item, parent, false);
        RecyclerViewTodoHolder recyclerViewAppointementItemHolder = new RecyclerViewTodoHolder(view);

        return recyclerViewAppointementItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTodoHolder holder, int position) {
        ToDo toDo = data.get(position);
        holder.getDate().setText(toDo.getDate());
        holder.getCommentaire().setText(toDo.getCommentaire());
        holder.getCheckBox().setSelected(toDo.isDone());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewTodoHolder extends RecyclerView.ViewHolder {
        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

        public TextView getCommentaire() {
            return commentaire;
        }

        public void setCommentaire(TextView commentaire) {
            this.commentaire = commentaire;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        TextView date, commentaire;
        CheckBox checkBox;

        public RecyclerViewTodoHolder(View itemView) {

            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            commentaire = (TextView) itemView.findViewById(R.id.commentaire);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void add(ToDo item) {
        data.add(item);
        notifyItemInserted(data.size()-1);
    }
}
