package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tlayout);
        mViewPager = findViewById(R.id.vp);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adding fragment here
        viewPagerAdapter.AddFragment(new Posts(), "POSTS");
        viewPagerAdapter.AddFragment(new Messages(), "MESSAGES");
        viewPagerAdapter.AddFragment(new Notifications(), "NOTIFICATIONS");

        mViewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        //

        //adding post list

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().subscribeToTopic("general");
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.i(TAG, "failed");
                    return;
                }
                String r = task.getResult().getToken();

                Log.i(TAG, task.getResult().getToken());
            }
        });

    }

}


//dp0Jg1utRq6xplpyry9Vbs:APA91bH5RQak2PagaRedbyEN6ScwpD537xFGva1oAXFtVqKXj3aslN5wxCRA2QxN5Nnze1FYy1chYVCxtnFitxPrekeXRK0tMbYgEM2S6AGEnX2TGXCDYxfBm-VHoNkcJttLJdDw5C3i