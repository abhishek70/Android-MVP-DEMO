package com.example.abhishek.android_mvp_demo.login;

import com.example.abhishek.android_mvp_demo.data.User;

/**
 * Created by abhishek on 1/16/17.
 * This specifies the contract between the view and the presenter.
 */

interface LoginContract {

    interface LoginView {

        void showUsernameEmptyError(String msg);

        void showPasswordEmptyError(String msg);

        void showLoginInvalidError(String msg);

        void showLoading(boolean isLoading);

        void showDashboardActivity();

    }

    interface LoginActionListener {

        void checkAuth(User user);

    }

}
