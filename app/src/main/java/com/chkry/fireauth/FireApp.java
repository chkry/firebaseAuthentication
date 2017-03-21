package com.chkry.fireauth;

import android.app.Application;
import com.firebase.client.Firebase;

import android.app.Application;

/**
 * Created by chakradhar.reddy on 3/21/2017.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
