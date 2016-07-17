package com.moizest89.androidchat.Login;

/**
 * Created by @moizest89 in SV on 7/14/16.
 */
public interface LoginPresenter {

    void OnDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
