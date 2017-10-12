package artsam.com.mypolice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import artsam.com.mypolice.BidAdapter;
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
 * Created by Artem on 09.10.17.
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    public static final String AUTHENTICATED_USER = "Authenticated_user";
    public static final String MY_PREF = "My_preferences";
    public static final int REQUEST_LOGIN = 1;

    private SharedPreferences sPref;
    private MyPoliceClient myPoliceClient;

    @Bind(R.id.rv_bids)
    RecyclerView mRvBids;

    private BidAdapter mBidAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BidFromServer> bidsFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        bidsFromServer = new ArrayList<>();

        ButterKnife.bind(this);
        mRvBids.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRvBids.setLayoutManager(mLayoutManager);
        mBidAdapter = new BidAdapter(bidsFromServer);
        mRvBids.setAdapter(mBidAdapter);

        if (getCredentials().isEmpty()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
        } else {
            initClient(getCredentials());
        }

    }

    private String getCredentials() {
        return sPref.getString(AUTHENTICATED_USER, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            initClient(getCredentials());
        }
    }

    private void initClient(String credentials) {
        myPoliceClient = ClientsBuilder.getMyPoliceClient(credentials);
        myPoliceClient.getLostChildBids(0, 10).enqueue(bidsCallback);
    }

    Callback<List<BidFromServer>> bidsCallback = new Callback<List<BidFromServer>>() {
        @Override
        public void onResponse(Call<List<BidFromServer>> call, Response<List<BidFromServer>> response) {
            mBidAdapter.update(response.body());
        }

        @Override
        public void onFailure(Call<List<BidFromServer>> call, Throwable t) {
            Log.d(TAG, "onFailure()");
            t.printStackTrace();
            Toast.makeText(getBaseContext(),
                    R.string.error_no_connection, Toast.LENGTH_LONG).show();
        }
    };
}
