package com.moizest89.androidchat.Login;

/**
 * Created by @moizest89 in SV on 7/17/16.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository =  new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        this.loginRepository.checkSession();
    }

    @Override
    public void doSignUp(String email, String password) {
        this.loginRepository.signUp(email, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        this.loginRepository.signIn(email, password);
    }
}
