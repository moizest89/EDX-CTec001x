package com.moizest89.androidchat.Login;

/**
 * Created by @moizest89 in SV on 7/14/16.
 */
public interface LoginView {

    void enableInputs();
    void desableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
