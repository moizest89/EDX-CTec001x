package com.moizest89.androidchat.Login;

/**
 * Created by @moizest89 in SV on 7/15/16.
 */
public class LoginPresenterImpl implements LoginPresenter{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }



    @Override
    public void OnDestroy() {
        this.loginView = null;
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
