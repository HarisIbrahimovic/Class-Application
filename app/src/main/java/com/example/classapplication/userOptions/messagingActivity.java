package com.example.classapplication.userOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.classapplication.R;
import com.example.classapplication.adapters.MyAdapterMessages;
import com.example.classapplication.message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class messagingActivity extends AppCompatActivity {
    private EditText message2;
    private DatabaseReference databaseReference;
    private TextView reciverName;
    private ImageButton sendButton;
    private String reciverId;
    private String senderId;
    private RecyclerView recyclerView;
    private String messContent;
    private String ReciverName;
    private FirebaseAuth auth;
    private ArrayList<message> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        getIncomingIntent();
        configWidgets();
        readMessages(senderId,reciverId);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messContent = message2.getText().toString();
                if(TextUtils.isEmpty(messContent))return;
                databaseReference = FirebaseDatabase.getInstance().getReference("Messages");
                HashMap<String,String> Message = new HashMap<>();
                Message.put("content",messContent);
                Message.put("reciverId",reciverId);
                Message.put("senderId",senderId);
                databaseReference.push().setValue(Message);
                message2.setText("");
            }
        });
    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra("username")&&getIntent().hasExtra("id")){
            reciverId = getIntent().getStringExtra("id");
            ReciverName = getIntent().getStringExtra("username");
       }
    }
    private void configWidgets(){
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerViewMess);
        reciverName = findViewById(R.id.messReciverName);
        message2 = findViewById(R.id.textSend);
        sendButton = findViewById(R.id.sendButton);
        senderId = auth.getCurrentUser().getUid().toString();
        reciverName.setText(ReciverName);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void readMessages(String senderId2,String reciverId2){
        messages = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Messages");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    message curMessage = snapshot1.getValue(message.class);
                    if(curMessage.getReciverId().equals(senderId2)&& curMessage.getSenderId().equals(reciverId2) || curMessage.getReciverId().equals(reciverId2)&& curMessage.getSenderId().equals(senderId2)){
                        messages.add(curMessage);
                  }
                    MyAdapterMessages myAdapterMessages = new MyAdapterMessages(messages,getApplicationContext());
                    recyclerView.setAdapter(myAdapterMessages);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}