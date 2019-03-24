package com.example.khanacademy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;


    public MyAdapter(List<ListItem> listItem, Context context) {
        this.listItems = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListItem listItem = listItems.get(i);

        viewHolder.txtStudent.setText(listItem.getStudentname());
        viewHolder.txtHours.setText(listItem.getHours());
        viewHolder.txtProblems.setText(listItem.getProblems());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtStudent;
        public TextView txtHours;
        public TextView txtProblems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStudent = (TextView) itemView.findViewById(R.id.student_name);
            txtHours = (TextView) itemView.findViewById(R.id.student_hours);
            txtProblems = (TextView) itemView.findViewById(R.id.student_problems);
        }
    }

}
