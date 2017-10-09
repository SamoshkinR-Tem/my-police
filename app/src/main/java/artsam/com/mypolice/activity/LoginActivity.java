package artsam.com.mypolice.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import artsam.com.mypolice.R;
import artsam.com.mypolice.client.ClientsBuilder;
import artsam.com.mypolice.client.MyPoliceClient;
import artsam.com.mypolice.models.BidFromServer;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Bind(R.id.login_form)
    View mLoginFormView;
    @Bind(R.id.login_progress)
    View mProgressView;
    @Bind(R.id.login)
    EditText mLoginView;
    @Bind(R.id.password)
    EditText mPasswordView;
    @Bind(R.id.btnSignIn)
    Button mSignInBtn;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mSignInBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    private void attemptLogin() {
        Log.d(TAG, "attemptLogin()");

        // Store values at the time of the login attempt.
        String login = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (mAuthTask == null && validate(login, password)) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(login, password);
            mAuthTask.execute((Void) null);
        } else {
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validate(String login, String password) {
        mLoginView.setError(null);
        mPasswordView.setError(null);

        // Check for a valid login.
        if (login.isEmpty()) {
            mLoginView.setError(getString(R.string.error_field_required));
            mLoginView.requestFocus();
            return false;
        }

        // Check for a valid password, if the user entered one.
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mLogin;
        private final String mPassword;

        UserLoginTask(String login, String password) {
            mLogin = login;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            MyPoliceClient myPoliceClient = ClientsBuilder.getMyPoliceClient(mLogin, mPassword);
            myPoliceClient.getLostChildBids(0, 2).enqueue(bidsCallback);
            return true;
        }

        Callback<List<BidFromServer>> bidsCallback = new Callback<List<BidFromServer>>() {
            @Override
            public void onResponse(Call<List<BidFromServer>> call, Response<List<BidFromServer>> response) {
                Log.d(TAG, "onResponse()");
                if (response.isSuccessful()) {
                    List<BidFromServer> bids = response.body();
                    Log.d(TAG, bids.get(0).getId() + "; " +
                            bids.get(0).getName() + "; " +
                            bids.get(0).getDateOfBirth());
                } else {
                    Log.d(TAG, "bidsCallback" +
                            " Code: " + response.code() +
                            " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BidFromServer>> call, Throwable t) {
                t.printStackTrace();
            }
        };

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

