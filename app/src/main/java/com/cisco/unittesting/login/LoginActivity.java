package com.cisco.unittesting.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cisco.unittesting.MainActivity;
import com.cisco.unittesting.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    Button mButtonLogin;
    TextView mViewEmail, mViewPassword;
    LoginAPI mLoginAPI;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewEmail = (TextView) findViewById(R.id.emailId);
        mViewPassword = (TextView) findViewById(R.id.password);
        mButtonLogin = (Button) findViewById(R.id.btnLogin);
        mButtonLogin.setOnClickListener(mOnClickListener);
        mLoginAPI = new LoginAPI();

        mLoginPresenter = new LoginPresenter(this, mLoginAPI);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mLoginPresenter.onLoginButtonClick();
        }
    };


    @Override
    public String getUsername() {
        return mViewEmail.getText().toString();
    }

    @Override
    public void showEmptyUsernameErrorMessage(@StringRes int resId) {
        mViewEmail.setError(getString(resId));
    }

    @Override
    public String getPassword() {
        return mViewPassword.getText().toString();
    }

    @Override
    public void showEmptyPasswordErrorMessage(int empty_password_error) {
        mViewPassword.setError(getString(empty_password_error));
    }

    @Override
    public void showUnsuccessfulAuthenticationMessage() {
        Toast.makeText(this, R.string.incorrect_username_or_password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public  void showLoginAttemptExceededAlert(){
        Toast.makeText(this,R.string.login_attempt_exceeded_error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
