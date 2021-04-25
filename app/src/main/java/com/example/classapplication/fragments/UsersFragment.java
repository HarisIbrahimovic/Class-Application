package com.example.classapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.classapplication.MainActivity;
import com.example.classapplication.R;
import com.example.classapplication.SignIn.SignUp;
import com.example.classapplication.authuser.deleteUser;
import com.example.classapplication.authuser.displayAllUsersActivity;

public class UsersFragment extends Fragment {
    private Button showAll;
    private Button addUser;
    private Button deleteUser;
    private Button modiflyUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        addUser = view.findViewById(R.id.AddUser);
        modiflyUser = view.findViewById(R.id.modifyUser);
        deleteUser = view.findViewById(R.id.deleteUser);
        showAll = view.findViewById(R.id.showAllUsers);
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), displayAllUsersActivity.class));
            }
        });
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), SignUp.class));
            }
        });
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), com.example.classapplication.authuser.deleteUser.class));
            }
        });
        modiflyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), com.example.classapplication.authuser.modifyActivity.class));
            }
        });
        return view;
    }
}