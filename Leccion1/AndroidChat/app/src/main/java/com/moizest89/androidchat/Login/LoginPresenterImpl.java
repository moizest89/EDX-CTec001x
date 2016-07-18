package com.moizest89.androidchat.Login;

import android.nfc.Tag;
import android.util.Log;

import com.moizest89.androidchat.Login.Events.LoginEvent;
import com.moizest89.androidchat.lib.EventBus;
import com.moizest89.androidchat.lib.GreenRobotEventBus;


/**
 * Created by @moizest89 in SV on 7/15/16.
 */
public class LoginPresenterImpl implements LoginPresenter{

    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private final static String TAG = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onDestroy() {
        this.loginView = null;
        this.eventBus.unregister(this);
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void checkForAuthenticatedUser() {

        checkView();
        this.loginInteractor.checkSession();

    }

    @Override
    public void validateLogin(String email, String password) {

        checkView();

        this.loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {

        checkView();

        this.loginInteractor.doSignUp(email, password);

    }

    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverySession:
                onFailedRoRecoverySession();
                break;
        }
    }


    private void onFailedRoRecoverySession() {
        checkView();
        Log.e(TAG, "onFailedRoRecoverySession");


    }


    private void onSignInSuccess(){
        if(this.loginView !=null){
            loginView.navigateToMainScreen();
        }
    }


    private void onSignInError(String error){
        if(this.loginView !=null){
            this.loginView.hideProgress();
            this.loginView.enableInputs();
            this.loginView.loginError(error);
        }
    }


    private void onSignUpSuccess(){
        if(this.loginView !=null){
            this.loginView.newUserSuccess();
        }
    }


    private void onSignUpError(String error){
        if(this.loginView !=null){
            this.loginView.hideProgress();
            this.loginView.enableInputs();
            this.loginView.newUserError(error);
        }
    }

    private void checkView(){
        if(this.loginView !=null){
            this.loginView.desableInputs();
            this.loginView.showProgress();
        }
    }
}
