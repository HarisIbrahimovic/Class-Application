package com.example.classapplication.FireApp;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class FireApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
