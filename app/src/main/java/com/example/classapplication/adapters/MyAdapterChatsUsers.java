package com.example.classapplication.adapters;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classapplication.R;
import com.example.classapplication.user;

import java.util.ArrayList;

public class MyAdapterChatsUsers extends RecyclerView.Adapter<MyAdapterChatsUsers.MyViewHolder> {
    Context context;
    ArrayList<user> users;


    public MyAdapterChatsUsers(Context context, ArrayList<user> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyAdapterChatsUsers.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.user_item_chat,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterChatsUsers.MyViewHolder holder, int position) {
        user User = users.get(position);
        byte[] decodedBytes = Base64.decode(User.getUsername().getBytes(),Base64.DEFAULT);
        holder.userName.setText(new String(decodedBytes));
        holder.userCourse.setText(User.getCourse());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView userName = itemView.findViewById(R.id.userNameChats);
        TextView userCourse = itemView.findViewById(R.id.userCourseChats);
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
