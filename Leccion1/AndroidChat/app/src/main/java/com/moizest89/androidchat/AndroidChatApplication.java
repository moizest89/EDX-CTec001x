package com.moizest89.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by @moizest89 in SV on 7/12/16.
 */
public class AndroidChatApplication extends Application{



    @Override
    public void onCreate() {
        super.onCreate();

        setUpFireBase();

    }

    private void setUpFireBase(){
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
