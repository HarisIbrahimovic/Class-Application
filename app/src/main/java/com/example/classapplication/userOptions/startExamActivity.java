package com.example.classapplication.userOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.connection.ListenHashProvider;

import java.util.HashMap;

public class startExamActivity extends AppCompatActivity {
    private String name,q1,q2,q3,a1,a2,a3;
    private TextView Name, Q1, Q2 ,Q3;
    private EditText A1, A2, A3;
    private Button submit;
    private int points;
    private int done;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);
        getIncomingintent();
        Name = findViewById(R.id.testName);
        Q1 = findViewById(R.id.Question1);
        Q2 = findViewById(R.id.Question2);
        Q3 = findViewById(R.id.Question3);
        A1 = findViewById(R.id.Answer1);
        A2 = findViewById(R.id.Answer2);
        A3 = findViewById(R.id.Answer3);
        Name.setText(name);
        Q1.setText(q1);
        Q2.setText(q2);
        Q3.setText(q3);
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(done==1){
                    Toast.makeText(getApplicationContext(), "Test already done", Toast.LENGTH_SHORT).show();
                    return;
                }
                String anw1 = A1.getText().toString();
                String anw2 = A2.getText().toString();
                String anw3 = A3.getText().toString();
                if(TextUtils.isEmpty(anw1)||TextUtils.isEmpty(anw2)||TextUtils.isEmpty(anw3)){
                    Toast.makeText(getApplicationContext(),"Fill in the fields.",Toast.LENGTH_SHORT).show();
                    return;
                }
                done = 1;
                points = 0;
                Toast.makeText(getApplicationContext(), "You have won "+points+" points", Toast.LENGTH_SHORT).show();
                auth = FirebaseAuth.getInstance();
                String userEmail = auth.getCurrentUser().getEmail();
                databaseReference = FirebaseDatabase.getInstance().getReference("Exam Results");
                HashMap<String, String> examRes = new HashMap<>();
                examRes.put("email",userEmail);
                examRes.put("points",""+points);
                examRes.put("test",name);
                databaseReference.push().setValue(examRes);
                finish();
            }
        });
    }
    private void getIncomingintent(){
        if(getIntent().hasExtra("name")&&getIntent().hasExtra("q1")&&
                getIntent().hasExtra("q2")&&getIntent().hasExtra("q3")&&getIntent().hasExtra("a1")&&
                getIntent().hasExtra("a2")&&getIntent().hasExtra("a3")){
             name = getIntent().getStringExtra("name");
             q1 = getIntent().getStringExtra("q1");
             q2 = getIntent().getStringExtra("q2");
             q3 = getIntent().getStringExtra("q3");
             a1 = getIntent().getStringExtra("a1");
             a2 = getIntent().getStringExtra("a2");
             a3 = getIntent().getStringExtra("a3");

        }
    }

    void addPoints(String anw1,String anw2, String anw3){
        if(anw1.equals(a1))points+=5;
        if(anw2.equals(a2))points+=5;
        if(anw1.equals(a3))points+=5;

    }
}