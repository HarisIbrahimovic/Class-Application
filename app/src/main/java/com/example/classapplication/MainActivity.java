package com.example.classapplication;

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

import com.example.classapplication.SignIn.loginActivity;
import com.example.classapplication.fragments.UsersFragment;
import com.example.classapplication.fragments.Exams;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FloatingActionButton logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null) {
            startActivity(new Intent(getApplicationContext(), loginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        logOutButton = findViewById(R.id.logoutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(),loginActivity.class));
                finish();
            }
        });

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
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
        viewPagerAdapter.addFragment(new UsersFragment(),"Users");
        viewPagerAdapter.addFragment(new Exams(),"Exam");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



}
