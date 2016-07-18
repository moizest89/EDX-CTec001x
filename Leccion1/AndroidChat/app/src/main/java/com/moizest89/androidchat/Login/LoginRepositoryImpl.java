package com.moizest89.androidchat.Login;

import android.content.Context;
import android.util.Log;

import com.moizest89.androidchat.Domain.FireBaseHelper;
import com.moizest89.androidchat.Login.Events.LoginEvent;
import com.moizest89.androidchat.lib.EventBus;
import com.moizest89.androidchat.lib.GreenRobotEventBus;

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
        postEvent(LoginEvent.onSignUpSuccess);
    }

    @Override
    public void signIn(String email, String password) {
        postEvent(LoginEvent.onSignInSuccess);
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverySession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);

        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
