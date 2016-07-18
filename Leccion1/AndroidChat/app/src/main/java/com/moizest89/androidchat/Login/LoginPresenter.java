package com.moizest89.androidchat.Login;

import com.moizest89.androidchat.Login.Events.LoginEvent;

/**
 * Created by @moizest89 in SV on 7/14/16.
 */
public interface LoginPresenter {


    void onDestroy();
    void onCreate();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);

    void onEventMainThread(LoginEvent event);
}
