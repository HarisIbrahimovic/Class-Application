package com.example.classapplication.userOptions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.classapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class messagingActivity extends AppCompatActivity {
    private EditText message;
    private DatabaseReference databaseReference;
    private ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        message = findViewById(R.id.textSend);
        sendButton = findViewById(R.id.sendButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("Messages");

    }
}