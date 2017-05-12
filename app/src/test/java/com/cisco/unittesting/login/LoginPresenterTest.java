package com.cisco.unittesting.login;

import com.cisco.unittesting.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



import static org.junit.Assert.assertTrue;

/**
 * Created by pchangul on 12/12/2016.
 */
public class LoginPresenterTest {

    private LoginPresenter mLoginPresenter;
    private LoginContract.View mView;
    private LoginAPI mLoginAPI;
    private AppStatus mAppStatus;

    @Before
    public void setUp() throws Exception {

        mView = Mockito.mock(LoginContract.View.class);
        mLoginAPI = Mockito.mock(LoginAPI.class);
        mAppStatus = Mockito.mock(AppStatus.class);
        mLoginPresenter = new LoginPresenter(mView, mLoginAPI);
    }

    @Test
    public void whenUsernameIsEmptyShowError() throws Exception {
        Mockito.when(mView.getUsername()).thenReturn("");
        mLoginPresenter.onLoginButtonClick();


        Mockito.verify(mView).showEmptyUsernameErrorMessage(R.string.empty_username_error);
    }

    @Test
    public void whenPasswordIsEmptyShowError() throws Exception {
        Mockito.when(mView.getUsername()).thenReturn("param");
        Mockito.when(mView.getPassword()).thenReturn("");
        mLoginPresenter.onLoginButtonClick();
        Mockito.verify(mView).showEmptyPasswordErrorMessage(R.string.empty_password_error);
    }

    @Test
    public void loginAttemptIsExceeded() throws Exception {
        Assert.assertEquals(1, mLoginPresenter.incrementLoginAttempt());
        Assert.assertEquals(2, mLoginPresenter.incrementLoginAttempt());
        Assert.assertEquals(3, mLoginPresenter.incrementLoginAttempt());
        Assert.assertTrue(mLoginPresenter.isLoginAttemptExceeded());
        mLoginPresenter.onLoginButtonClick();
        Mockito.verify(mView).showLoginAttemptExceededAlert();
    }

    @Test
    public void checkIsConnectedToInternet() throws Exception {
        Mockito.when(mAppStatus.isOnline()).thenReturn(true);
        mLoginPresenter.onLoginButtonClick();

    }

    @Test
    public void whenUsernameAndPasswordNotEmptyTestOnServerWithWrongCredentials() throws Exception {
        Mockito.when(mView.getUsername()).thenReturn("param1");
        Mockito.when(mView.getPassword()).thenReturn("123456");
        Mockito.when(mLoginAPI.authenticate("param", "12345")).thenReturn(false);
        mLoginPresenter.onLoginButtonClick();
        Mockito.verify(mView).showUnsuccessfulAuthenticationMessage();
    }

    @Test(expected = NullPointerException.class)
    public void nullStringTest() {
        String str = null;
        assertTrue(str.isEmpty());
    }

    @Test
    public void whenUsernameAndPasswordNotEmptyTestOnServerWithRightCredentials() throws Exception {
        Mockito.when(mView.getUsername()).thenReturn("param");
        Mockito.when(mView.getPassword()).thenReturn("12345");
        Mockito.when(mLoginAPI.authenticate("param", "12345")).thenReturn(true);
        mLoginPresenter.onLoginButtonClick();
        Mockito.verify(mView).startMainActivity();


    }

    @After
    public void tearDown() throws Exception {
        mView = null;
        mLoginPresenter = null;
        mLoginAPI = null;

    }
}