package artsam.com.mypolice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import artsam.com.mypolice.R;

/**
 * Created by Artem on 09.10.17.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
