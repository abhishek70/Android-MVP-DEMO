package com.example.abhishek.android_mvp_demo.login;

import com.example.abhishek.android_mvp_demo.data.LoginService;
import com.example.abhishek.android_mvp_demo.data.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by abhishek on 1/23/17.
 */

/**
 * Unit tests for the implementation of {@link LoginPresenter}.
 */
public class LoginPresenterTest {

    @Mock
    private LoginContract.LoginView loginView;

    @Mock
    private LoginService loginService;

    @Mock
    private User user;

    private LoginPresenter loginPresenter;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        // Setting up the Login Presenter
        loginPresenter = new LoginPresenter(loginView, loginService);
    }

    @Test
    public void shouldUsernameEntered() throws Exception {

        // username is not provided
        when(user.getUsername()).thenReturn("");

        // checking user credentials
        loginPresenter.checkAuth(user);

        // Verify the actual and expected result
        verify(loginView).showUsernameEmptyError("Please enter username");

    }


    @Test
    public void shouldPasswordEntered() throws Exception {

        // username is provided
        when(user.getUsername()).thenReturn("admin");

        // password is not provided
        when(user.getPassword()).thenReturn("");

        // checking user credentials
        loginPresenter.checkAuth(user);

        // Verify the actual and expected result
        verify(loginView).showPasswordEmptyError("Please enter password");

    }

    @Test
    public void shouldVerifyLogin() throws Exception {

        // username is provided
        when(user.getUsername()).thenReturn("admin");

        // password is provided
        when(user.getPassword()).thenReturn("demo");

        // assuming user credentials are correct
        when(loginService.authenticate("admin", "demo")).thenReturn(true);

        // checking user credentials
        loginPresenter.checkAuth(user);

        // Verify the actual and expected result
        verify(loginView).showDashboardActivity();
    }

    @Test
    public void shouldVerifyInvalidLogin() throws Exception {

        // username is provided
        when(user.getUsername()).thenReturn("admin");

        // password is provided
        when(user.getPassword()).thenReturn("demo");

        // assuming user credentials are invalid
        when(loginService.authenticate("admin", "demo")).thenReturn(false);

        // checking user credentials
        loginPresenter.checkAuth(user);

        // Verify the actual and expected result
        verify(loginView).showLoginInvalidError("Invalid Login !!!");
    }
}