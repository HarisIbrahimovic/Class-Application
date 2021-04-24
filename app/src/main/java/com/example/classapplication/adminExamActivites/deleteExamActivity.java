package com.example.classapplication.adminExamActivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deleteExamActivity extends AppCompatActivity {
    private Button deleteButton;
    private EditText examName;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_exam);
        deleteButton = findViewById(R.id.deleteExamButton);
        examName = findViewById(R.id.deleteExamName);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = examName.getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Exams").child(name);
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Succesfully deleted.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                Toast.makeText(getApplicationContext(),"Test does not exist.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}