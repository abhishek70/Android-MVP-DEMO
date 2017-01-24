package com.example.abhishek.android_mvp_demo.login;

import android.util.Log;
import android.widget.TextView;

import com.example.abhishek.android_mvp_demo.R;
import com.example.abhishek.android_mvp_demo.data.LoginService;
import com.example.abhishek.android_mvp_demo.data.User;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by abhishek on 1/16/17.
 */

public class LoginPresenter implements LoginContract.LoginActionListener {

    private final LoginContract.LoginView mLoginView;
    private LoginService mService;

    /**
     * Constructor
     * @param loginView
     * @param service
     */
    public LoginPresenter(LoginContract.LoginView loginView, LoginService service) {

        mLoginView = loginView;
        mService   = service;
    }

    /**
     * Method for authenticating user credentials
     * @param user
     */
    @Override
    public void checkAuth(User user) {

        // Validating username
        if(user.getUsername().isEmpty()) {
            mLoginView.showUsernameEmptyError("Please enter username");
            return;
        }

        mLoginView.showUsernameEmptyError("");

        // Validating password
        if(user.getPassword().isEmpty()) {
            mLoginView.showPasswordEmptyError("Please enter password");
            return;
        }

        mLoginView.showPasswordEmptyError("");

        // Showing progress dialog
        mLoginView.showLoading(true);

        // Checking the user credentials with login service
        boolean retLoginMsg = mService.authenticate(user.getUsername(), user.getPassword());

        // Removing progess dialog
        mLoginView.showLoading(false);

        /* Take user to Dashboard activity if user credentials are valid else show
           invalid login error */
        if(retLoginMsg){
            mLoginView.showDashboardActivity();
        } else {
            mLoginView.showLoginInvalidError("Invalid Login !!!");
        }

    }
}
