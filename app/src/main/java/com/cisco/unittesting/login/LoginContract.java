package com.cisco.unittesting.login;

import android.support.annotation.StringRes;

/**
 * Created by pchangul on 12/12/2016.
 */

public class LoginContract {

    public interface View {

        String getUsername();

        void showEmptyUsernameErrorMessage(@StringRes int resId);

        String getPassword();

        void showEmptyPasswordErrorMessage(@StringRes int empty_password_error);

        void showUnsuccessfulAuthenticationMessage();

        void startMainActivity();

        void showLoginAttemptExceededAlert();
    }
}
