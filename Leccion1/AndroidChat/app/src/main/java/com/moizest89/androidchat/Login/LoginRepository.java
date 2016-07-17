package com.moizest89.androidchat.Login;

/**
 * Created by @moizest89 in SV on 7/14/16.
 */
public interface LoginRepository {

    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
