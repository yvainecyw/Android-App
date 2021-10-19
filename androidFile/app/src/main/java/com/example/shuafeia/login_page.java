package com.example.shuafeia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class login_page extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    Button submit,register;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        progressBar = (ProgressBar)findViewById(R.id.login_progressBar);
        email = (EditText)findViewById(R.id.login_email_editText);
        password= (EditText)findViewById(R.id.login_password_editText);
        submit = (Button)findViewById(R.id.login_create_account_button);
        register = (Button)findViewById(R.id.login_close_button);

        submit.setOnClickListener(this);
        register.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_create_account_button:
                //Toast.makeText(login_page.this, "Login", Toast.LENGTH_SHORT).show();
                //finish();
                login();
                break;
            case R.id.login_close_button:
                startActivity(new Intent(login_page.this,register_activity.class));
                break;
        }
    }

    private void login() {


        String EmailTxt = email.getText().toString();
        String PassTxt = password.getText().toString();


        if (!TextUtils.isEmpty(EmailTxt) && !TextUtils.isEmpty(PassTxt)) {
            progressBar.setVisibility(View.VISIBLE);

            auth.signInWithEmailAndPassword(EmailTxt,PassTxt).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String token_id = FirebaseInstanceId.getInstance().getToken();
                        //Log.d("kaobe",token_id);
                        String user_id = auth.getCurrentUser().getUid();

                        DatabaseReference user_data = databaseReference.child(user_id);
                        startActivity(new Intent(login_page.this,MainActivity.class));
                        user_data.child("token_id").setValue(token_id).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressBar.setVisibility(View.INVISIBLE);
                                startActivity(new Intent(login_page.this,MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(login_page.this,"Failure",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(login_page.this,"Login Error",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });

        }
    }

}