package com.cisco.unittesting.login;

import com.cisco.unittesting.R;

/**
 * Created by pchangul on 12/12/2016.
 */

class LoginPresenter {

    private static final int MAX_LOGIN_ATTEMPT = 3;
    private final LoginContract.View mView;
    private final LoginAPI mLoginAPI;
    private int loginAttempt;

    LoginPresenter(LoginContract.View view, LoginAPI loginAPI) {

        mView = view;
        mLoginAPI = loginAPI;
    }

    public boolean isLoginAttemptExceeded() {
        return loginAttempt >= MAX_LOGIN_ATTEMPT;
    }

    public int incrementLoginAttempt() {
        loginAttempt = loginAttempt + 1;
        return loginAttempt;
    }

    void onLoginButtonClick() {

        String username = mView.getUsername();
        if (username.isEmpty()) {
            mView.showEmptyUsernameErrorMessage(R.string.empty_username_error);
            return;
        }


        String password = mView.getPassword();
        if (password.isEmpty()) {
            mView.showEmptyPasswordErrorMessage(R.string.empty_password_error);
            return;
        }


        if (mLoginAPI.authenticate(username, password)) {
            loginAttempt=0;
            mView.startMainActivity();
            return;
        }
        incrementLoginAttempt();
        mView.showUnsuccessfulAuthenticationMessage();
        if(isLoginAttemptExceeded()){
            mView.showLoginAttemptExceededAlert();
        }
    }
}
