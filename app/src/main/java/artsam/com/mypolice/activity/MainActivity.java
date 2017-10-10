package artsam.com.mypolice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import artsam.com.mypolice.R;

/**
 * Created by Artem on 09.10.17.
 */
public class MainActivity extends Activity {

    private static final int REQUEST_LOGIN_AND_GET_BIDS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN_AND_GET_BIDS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_LOGIN_AND_GET_BIDS:
            }
        }
    }
}
