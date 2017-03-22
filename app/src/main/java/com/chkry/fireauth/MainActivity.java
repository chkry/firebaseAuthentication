package com.chkry.fireauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button btn;
    private EditText pwd;
    private EditText mailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.loginbtn);
        pwd = (EditText)findViewById(R.id.password);
        mailid = (EditText)findViewById(R.id.email);


        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                        startanact();
                        MainActivity.this.finish();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });


    }// E.O.Oncreate

    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startanact(){
        startActivity(new Intent(MainActivity.this,AccountActivity.class));
    }


    private void startSignIn(){
        String emailad = mailid.getText().toString();
        String pswrd = pwd.getText().toString();
        if(TextUtils.isEmpty(emailad) || TextUtils.isEmpty(pswrd)){
            Toast.makeText(MainActivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();

        } else {

            mAuth.signInWithEmailAndPassword(emailad,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "SignIn Problem", Toast.LENGTH_SHORT).show();
                    }
                }
            });
         }
    } //E.o>start Sign IN

}//E.O. Activity
