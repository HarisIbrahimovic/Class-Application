package com.example.classapplication.authuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.classapplication.R;
import com.example.classapplication.SignIn.loginActivity;
import com.example.classapplication.fragments.Test2Fragment;
import com.example.classapplication.fragments.TestFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class adminActivity extends AppCompatActivity {
    private FloatingActionButton logoutButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TabLayout tabLayout = findViewById(R.id.tabLayoutAdmin);
        ViewPager viewPager = findViewById(R.id.viewPagerAdmin);
        logoutButton = findViewById(R.id.logoutButtonAdmin);
        auth = FirebaseAuth.getInstance();
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), loginActivity.class));
            }
        });

        class ViewPagerAdapter extends FragmentPagerAdapter {
            private ArrayList<Fragment> fragments;
            private ArrayList<String> titles;
            ViewPagerAdapter(FragmentManager fm){
                super(fm);
                this.fragments = new ArrayList<>();
                this.titles = new ArrayList<>();
            }
            
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }


            public void addFragment(Fragment fragment,String title){
                fragments.add(fragment);
                titles.add(title);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return  titles.get(position);
            }
        }
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Test2Fragment(),"Chats");
        viewPagerAdapter.addFragment(new TestFragment(),"Users");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}