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
import com.example.classapplication.adapters.MyAdapterExams;
import com.example.classapplication.exam;
import com.example.classapplication.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Examusers extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private MyAdapterExams myAdapter;
    private ArrayList<exam> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examusers, container, false);
        recyclerView =  view.findViewById(R.id.examRecyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Exams");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        list = new ArrayList<>();
        myAdapter = new MyAdapterExams(getActivity().getApplicationContext(),list);
        recyclerView.setAdapter(myAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    exam Exam = snapshot1.getValue(exam.class);
                    list.add(Exam);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
}