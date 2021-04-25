package com.example.classapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.classapplication.R;
import com.example.classapplication.adminExamActivites.createExamActivity;
import com.example.classapplication.adminExamActivites.deleteExamActivity;

public class Exams extends Fragment {
    private Button newExam;
    private Button deleteExam;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exams, container, false);
        newExam = view.findViewById(R.id.createExam);
        deleteExam = view.findViewById(R.id.deleteExam);
        newExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), createExamActivity.class));
            }
        });
        deleteExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), deleteExamActivity.class));
            }
        });
        return view;
    }
}