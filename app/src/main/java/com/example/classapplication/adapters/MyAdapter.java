package com.example.classapplication.adapters;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Base64.Decoder;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classapplication.R;
import com.example.classapplication.user;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<user> list;

    public MyAdapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        user User = list.get(position);
        byte[] decodedBytes = Base64.decode(User.getUsername().getBytes(),Base64.DEFAULT);

        holder.username.setText(new String(decodedBytes));
        holder.email.setText(User.getEmail());
        holder.course.setText(User.getCourse());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView username = itemView.findViewById(R.id.UserNameView);
        TextView email = itemView.findViewById(R.id.EmailView);
        TextView course = itemView.findViewById(R.id.CouseView);
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
