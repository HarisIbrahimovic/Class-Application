package com.example.classapplication.adminExamActivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class createExamActivity extends AppCompatActivity {
    private Button createButton;
    private EditText course;
    private EditText name;
    private EditText q1;
    private EditText q2;
    private EditText q3;
    private EditText a1;
    private EditText a2;
    private EditText a3;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        createButton = findViewById(R.id.createExamButton);
        course = findViewById(R.id.examCourse);
        name = findViewById(R.id.examName);
        q1 = findViewById(R.id.examque1);
        q2 = findViewById(R.id.examque2);
        q3 = findViewById(R.id.examque3);
        a1 = findViewById(R.id.examanw1);
        a2 = findViewById(R.id.examanw2);
        a3 = findViewById(R.id.examanw3);
        progressDialog = new ProgressDialog(this);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName= course.getText().toString();
                String examName = name.getText().toString();
                String que1 = q1.getText().toString();
                String que2 = q2.getText().toString();
                String que3 = q3.getText().toString();
                String anw1 = a1.getText().toString();
                String anw2 = a2.getText().toString();
                String anw3 = a3.getText().toString();
                if(TextUtils.isEmpty(courseName)||TextUtils.isEmpty(examName)||TextUtils.isEmpty(que1)||TextUtils.isEmpty(que2)||TextUtils.isEmpty(que3)||TextUtils.isEmpty(anw1)
                        ||TextUtils.isEmpty(anw2)||TextUtils.isEmpty(anw3)){
                    Toast.makeText(getApplicationContext(),"Fill in the fields",Toast.LENGTH_SHORT);
                    return;
                }
                progressDialog.setMessage("Processing");
                progressDialog.show();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Exams");
                HashMap<String,String> exam = new HashMap<>();
                exam.put("name",examName);
                exam.put("course",courseName);
                exam.put("q1",que1);
                exam.put("q2",que2);
                exam.put("q3",que3);
                exam.put("a1",anw1);
                exam.put("a2",anw2);
                exam.put("a3",anw3);
                databaseReference.push().setValue(exam).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Succesfully created",Toast.LENGTH_SHORT).show();
                            finish();}
                        else{
                                Toast.makeText(getApplicationContext(),"Problems..",Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
            }
        });



    }
}