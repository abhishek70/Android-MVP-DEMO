package com.example.abhishek.android_mvp_demo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;

import com.example.abhishek.android_mvp_demo.R;
import com.example.abhishek.android_mvp_demo.dashboard.DashboardActivity;
import com.example.abhishek.android_mvp_demo.data.LoginService;
import com.example.abhishek.android_mvp_demo.data.User;

/**
 * Created by abhishek on 1/16/17.
 */

public class LoginFragment extends Fragment implements LoginContract.LoginView {

    private LoginContract.LoginActionListener mActionListener;
    private EditText mUsername, mPassword;
    private Button mLoginBtn;
    private ProgressDialog progressDialog;

    public LoginFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Setting up the LoginPresenter
        mActionListener = new LoginPresenter(this, new LoginService());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Setting up the login fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        mUsername = (EditText) root.findViewById(R.id.username);
        mPassword = (EditText) root.findViewById(R.id.password);
        mLoginBtn = (Button) root.findViewById(R.id.login);

        // Setting up the listeners
        setUpActionListener();

        return root;
    }

    /**
     * Methods for setting up the listeners
     */
   public void setUpActionListener() {

       mLoginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               // User Obj
               User user = new User(mUsername.getText().toString(), mPassword.getText().toString());

               // Checking for the user authentication
               mActionListener.checkAuth(user);
           }
       });

   }

    /**
     * Method for showing error for empty username
     * @param msg
     */
    @Override
    public void showUsernameEmptyError(String msg) {

        if(msg.isEmpty())
            mUsername.setError(null);
        else
            mUsername.setError(msg);
    }

    /**
     * Method for showing error for password empty
     * @param msg
     */
    @Override
    public void showPasswordEmptyError(String msg) {

        if(msg.isEmpty())
            mPassword.setError(null);
        else
            mPassword.setError(msg);
    }

    /**
     * Method for showing error for invalid login using Snackbar
     * @param msg
     */
    @Override
    public void showLoginInvalidError(String msg) {

        // Verify whether view is available
        if (getView() != null) {
            Snackbar.make(getView(),msg,Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Method for showing progress dialog
     * @param isLoading
     */
    @Override
    public void showLoading(boolean isLoading) {

        if(isLoading) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        } else {
            progressDialog.dismiss();
        }
    }

    /**
     * Method for initiating dashboard activity after successful login
     */
    @Override
    public void showDashboardActivity() {

        // Setting up the intent
        Intent intent = new Intent(getContext(), DashboardActivity.class);

        // Starting dashboard activity
        startActivity(intent);
    }

}
