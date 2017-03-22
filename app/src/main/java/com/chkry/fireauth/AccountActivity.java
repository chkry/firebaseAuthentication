package com.chkry.fireauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    private Button logot;
    private EditText username;
    private EditText message;
    private Button updatebtn;
    private TextView valuevalue;
    private String TAG = "Account_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        logot = (Button)findViewById(R.id.logout);

        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                AccountActivity.this.finish();
            }


        }); //E.o.onClick


        username = (EditText)findViewById(R.id.username);
        message = (EditText)findViewById(R.id.message);
        updatebtn = (Button) findViewById(R.id.updatebutn);



        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrnm = username.getText().toString();

                String msgmsg = message.getText().toString();
                if(msgmsg.isEmpty()){
                    updateValues(usrnm);
                }else {
                    updateValues(usrnm,msgmsg);
                }


            }
        });











    } //E.o.OnCreate

    private void updateValues(String usrnm, String msgmsg){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference(usrnm);

        myRef.setValue(msgmsg);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                valuevalue = (TextView)findViewById(R.id.valueid);
                valuevalue.setText(value);
                Toast.makeText(AccountActivity.this, "DB value is : "+value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




    } //E.o.Update Values

    private void updateValues(String usrnm){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference(usrnm);



        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                valuevalue = (TextView)findViewById(R.id.valueid);
                valuevalue.setText(value);
                Toast.makeText(AccountActivity.this, "DB value is : "+value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




    } //E.o.Update Values


} // E.O.Activity
