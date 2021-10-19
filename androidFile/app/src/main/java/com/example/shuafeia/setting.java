package com.example.shuafeia;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shuafeia.model.Notification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class setting extends Fragment{
    private Button logout,ring;
    FirebaseAuth auth;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();

        View view =inflater.inflate(R.layout.fragment_setting, container, false);
        logout = view.findViewById(R.id.Logout_Button);
        ring = view.findViewById(R.id.Ringtone_Button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        return view;
    }

    private void sendMessage() {
        String user_id = "98GmcOhVpvWShRCITUuLY5j1aOE3";
        String user_name="chuaenyang";
        Intent intent = new Intent(this.getContext(),SendActivity.class);
        intent.putExtra("user_id",user_id);
        intent.putExtra("user_name",user_name);
        startActivity(intent);
    }


    private void Logout() {
        auth.signOut();
        Intent intent = new Intent(this.getContext(),login_page.class);
        startActivity(intent);
    }


}
