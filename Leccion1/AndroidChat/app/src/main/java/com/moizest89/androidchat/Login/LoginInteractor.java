package com.moizest89.androidchat.Login;

/**
 * Created by @moizest89 in SV on 7/14/16.
 */
public interface LoginInteractor {

    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);

}
