package com.example.classapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classapplication.R;
import com.example.classapplication.exam;
import com.example.classapplication.user;

import java.util.ArrayList;

public class MyAdapterExams extends RecyclerView.Adapter<MyAdapterExams.MyViewHolder> {
    Context context;
    ArrayList<exam> list;


    public MyAdapterExams(Context context, ArrayList<exam> list) {
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<exam> getList() {
        return list;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView examName = itemView.findViewById(R.id.testNameItem);
        TextView courseName = itemView.findViewById(R.id.courseItem);
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public MyAdapterExams.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.exam_item,parent,false);

        return new MyAdapterExams.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterExams.MyViewHolder holder, int position) {
        exam Exam = list.get(position);
        holder.courseName.setText(Exam.getCourse());
        holder.examName.setText(Exam.getName());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
