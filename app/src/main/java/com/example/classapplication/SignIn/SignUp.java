
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private Button button;
    private EditText email;
    private EditText password;
    private EditText cPassword;
    private EditText userName;
    private TextView signUpText;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button = findViewById(R.id.signupButton);
        email = findViewById(R.id.emailSignUp);
        password = findViewById(R.id.passSignUp);
        cPassword = findViewById(R.id.confirmPassword);
        signUpText = findViewById(R.id.signUpText);
        userName = findViewById(R.id.userName);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String confirm = cPassword.getText().toString();
                String name = userName.getText().toString();
                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)||TextUtils.isEmpty(confirm)||TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"Please fill in the fields!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Password.equals(confirm)){
                    Toast.makeText(getApplicationContext(),"Passwords do not match!",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Just a momment please!");
                progressDialog.show();
                RegisterNow(name,Email,Password);
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loginActivity.class));
                finish();
            }
        });




    }

    private void RegisterNow(String name, String userEmail,String userPass) {
        auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getApplicationContext(),"Succesfully created",Toast.LENGTH_SHORT);
                FirebaseUser firebaseUser = auth.getCurrentUser();
                String userId = firebaseUser.getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("My Users").child(userId);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id",userId);
                hashMap.put("username",name);
                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            progressDialog.dismiss();
                            finish();
                        }else progressDialog.dismiss();
                    }
                });
            }
        });
    }
}