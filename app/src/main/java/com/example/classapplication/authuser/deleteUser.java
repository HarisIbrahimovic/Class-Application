package com.example.classapplication.authuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classapplication.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class deleteUser extends AppCompatActivity {

    private Button deleteButton;
    private EditText email;
    private EditText passwrod;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        deleteButton = findViewById(R.id.deleteButton);
        email = findViewById(R.id.deleteEmail);
        passwrod = findViewById(R.id.deletePassword);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                String Email = email.getText().toString();
                String Pass = passwrod.getText().toString();

                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Pass)){
                    Toast.makeText(getApplicationContext(),"Fill in the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(Email,Pass);
                FirebaseUser user = auth.getCurrentUser();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User deleted",Toast.LENGTH_SHORT).show();
                        }else  Toast.makeText(getApplicationContext(),"User not",Toast.LENGTH_SHORT).show();
                    }
                });
                }
        });

    }
}