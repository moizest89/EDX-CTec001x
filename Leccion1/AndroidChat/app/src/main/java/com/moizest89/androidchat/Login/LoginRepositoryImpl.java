package com.moizest89.androidchat.Login;

import android.content.Context;
import android.util.Log;

import com.moizest89.androidchat.Domain.FireBaseHelper;

/**
 * Created by @moizest89 in SV on 7/17/16.
 */
public class LoginRepositoryImpl implements LoginRepository{


    private FireBaseHelper fireBaseHelper;
    private final static String TAG = LoginRepositoryImpl.class.getSimpleName();

    public LoginRepositoryImpl() {
        this.fireBaseHelper = FireBaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        Log.e(TAG, "singUp");
    }

    @Override
    public void signIn(String email, String password) {
        Log.e(TAG, "singIn");
    }

    @Override
    public void checkSession() {
        Log.e(TAG, "checkSession");
    }
}
