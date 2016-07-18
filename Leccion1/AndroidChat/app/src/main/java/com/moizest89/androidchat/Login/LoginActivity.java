package com.moizest89.androidchat.Login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.moizest89.androidchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.editTxtEmail)
    EditText inputEmail;

    @Bind(R.id.editTxtPassword)
    EditText inputPassword;

    @Bind(R.id.btnSignIn)
    Button buttonSignIn;

    @Bind(R.id.btnSignUp)
    Button buttonSignUp;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);



        setSupportActionBar(toolbar);


        this.mPresenter = new LoginPresenterImpl(this);
        this.mPresenter.onCreate();
        mPresenter.checkForAuthenticatedUser();


    }

    @OnClick(R.id.btnSignIn)
    public void handleSignin(View view){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void desableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        //
    }

    @Override
    public void hideProgress() {
        //
    }


    @OnClick(R.id.btnSignUp)
    @Override
    public void handleSignUp() {
        mPresenter.registerNewUser(
                this.inputEmail.getText().toString().trim(),
                this.inputPassword.getText().toString()
        );
    }

    @OnClick(R.id.btnSignIn)
    @Override
    public void handleSignIn() {
        mPresenter.validateLogin(
                this.inputEmail.getText().toString().trim(),
                this.inputPassword.getText().toString()
        );
    }

    @Override
    public void navigateToMainScreen() {
        //Create a new Intent
    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void newUserSuccess() {

    }

    @Override
    public void newUserError(String error) {

    }


    private void setInputs(boolean enable){

        this.inputEmail.setEnabled(enable);
        this.inputPassword.setEnabled(enable);
        this.buttonSignIn.setEnabled(enable);
        this.buttonSignUp.setEnabled(enable);
    }


    @Override
    protected void onDestroy() {
        this.mPresenter.onDestroy();
        super.onDestroy();
    }
}
