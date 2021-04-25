package com.example.classapplication.authuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.classapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class modifyActivity extends AppCompatActivity {
    private Button modifyButton;
    private EditText email;
    private EditText password;
    private EditText newEmail;
    private EditText newPassword;
    private EditText newUsername;
    private EditText newCourse;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        modifyButton = findViewById(R.id.modifyButton);
        email = findViewById(R.id.modifyEmail1);
        password = findViewById(R.id.modifyPassword1);
        newEmail = findViewById(R.id.newEmail);
        newPassword = findViewById(R.id.newPassword);
        newUsername = findViewById(R.id.modifyUsername);
        newCourse = findViewById(R.id.modifyCourse);
        progressDialog = new ProgressDialog(this);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String NewEmail = newEmail.getText().toString();
                String NewPassword = newPassword.getText().toString();
                String NewUsername = newUsername.getText().toString();
                String NewCourse = newCourse.getText().toString();
                modify(Email,Password,NewEmail,NewPassword,NewUsername,NewCourse);
            }
        });
    }
    public void modify(String Email, String Password, String NewEmail, String NewPassword,String NewUsername, String NewCourse){
        if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)||TextUtils.isEmpty(NewEmail)||TextUtils.isEmpty(NewPassword)||TextUtils.isEmpty(NewCourse)||TextUtils.isEmpty(NewUsername)){
            Toast.makeText(getApplicationContext(),"Please fill in the fields.",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!NewCourse.equals("IT")||!NewCourse.equals("CS")){
            Toast.makeText(getApplicationContext(),"Incorrect course. ",Toast.LENGTH_SHORT).show();
            return;}
        if(Password.length()<8){
            Toast.makeText(getApplicationContext(),"Password too short. ",Toast.LENGTH_SHORT).show();
            return;}
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
        auth.signInWithEmailAndPassword(Email,Password);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)            {
            user.updateEmail(NewEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(NewPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(),"Successfully modified.",Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        String userId = user.getUid();
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("My Users").child(userId);
                                        databaseReference.child("username").setValue(NewUsername);
                                        databaseReference.child("course").setValue(NewCourse);
                                        finish();
                                    }
                                });
                            }else {
                                Toast.makeText(getApplicationContext(),"User does not exist.",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });}
    }
}