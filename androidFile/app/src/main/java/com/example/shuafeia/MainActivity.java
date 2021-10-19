package com.example.shuafeia;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.Room1.Event;
import com.example.Room1.EventViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EventViewModel mEventViewModel;
    static final int RESULE_CODE = 1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();



        //loading the default fragment
        loadFragment(new calender());
        mEventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser()== null){
            startActivity(new Intent(this,login_page.class));
            finish();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new calender();
                break;

            case R.id.navigation_profile:
                fragment = new profile();
                break;

            case R.id.navigation_friend_list:
                fragment = new friend_list();
                break;

            case R.id.navigation_setting:
                fragment = new setting();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void addTask(View view) {
        Intent intent = new Intent(this,add_task.class);
        startActivityForResult(intent,RESULE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULE_CODE) {
            if (resultCode == RESULT_OK) {
                Event event = new Event(data.getStringExtra("Task"),
                        data.getStringExtra("Date"),
                        data.getStringExtra("Time"),
                        data.getBooleanExtra("Remind", false),
                        data.getStringExtra("Type"),
                        data.getIntExtra("Limit_hour",0),
                        data.getIntExtra("Limit_minue",0));
                //db.eventDao().insert(event);
                Log.d(LOG_TAG,"DateTime:"+event.getDateTime()+" Task Name:"+event.getTask()+" Date:"+event.getDate()+" Time:"+event.getTime()+" Remind:"+event.isRemind()+" Type:"+event.getType()+" Limit:"+event.getLimit_hour()+":"+event.getLimit_minue());
                mEventViewModel.insert(event);
                Log.d(LOG_TAG,"Insert sussceful");
            }
        }
    }

}