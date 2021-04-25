package com.example.classapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classapplication.R;
import com.example.classapplication.adapters.MyAdapter;
import com.example.classapplication.adapters.MyAdapterChatsUsers;
import com.example.classapplication.user;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class chatsFragment extends Fragment {
    private RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapterChatsUsers myAdapterChatsUsers;
    ArrayList<user> users;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.recViewChats);
        databaseReference = FirebaseDatabase.getInstance().getReference("My Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        users = new ArrayList<>();
        myAdapterChatsUsers = new MyAdapterChatsUsers(getActivity().getApplicationContext(),users);
        recyclerView.setAdapter(myAdapterChatsUsers);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user User;
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    User = snapshot1.getValue(user.class);
                    users.add(User);
                }
                myAdapterChatsUsers.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}