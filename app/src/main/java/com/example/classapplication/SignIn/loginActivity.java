package com.example.classapplication.SignIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classapplication.MainActivity;
import com.example.classapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private TextView loginText;
    private FirebaseAuth auth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginButton = findViewById(R.id.loginButton);
        loginText = findViewById(R.id.loginText);
        mDialog = new ProgressDialog(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)){
                    Toast.makeText(getApplicationContext(), "Please fill in the fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDialog.setMessage("Please wait...");
                mDialog.show();
                auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(getApplicationContext(),"Welcome..",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                            finish();
                        }else{
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Problems..",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
                finish();
            }
        });

    }
}