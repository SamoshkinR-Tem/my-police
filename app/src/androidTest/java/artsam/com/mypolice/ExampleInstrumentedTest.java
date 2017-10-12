package artsam.com.mypolice;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Timestamp;
import java.util.List;

import artsam.com.mypolice.client.ClientsBuilder;
import artsam.com.mypolice.client.MyPoliceClient;
import artsam.com.mypolice.models.BidFromServer;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Instrumentation test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String TAG = "ExampleInstrumentedTest";

    private static MyPoliceClient myPoliceClient;

    @BeforeClass
    public static void createMyPoliceClient() {
        myPoliceClient = ClientsBuilder.getMyPoliceClient(Credentials.basic("andrey", "and123and"));
    }

    @Test
    public void tryToGetLostChildrenBids() throws Exception {
        Log.d(TAG, new Timestamp(System.currentTimeMillis()).toString());

        myPoliceClient.getLostChildBids(0, 2).enqueue(bidsCallback);
    }

    Callback<List<BidFromServer>> bidsCallback = new Callback<List<BidFromServer>>() {
        @Override
        public void onResponse(Call<List<BidFromServer>> call, Response<List<BidFromServer>> response) {
            System.out.println("onResponse");
            if (response.isSuccessful()) {
                List<BidFromServer> bids = response.body();
                Log.d(TAG, bids.get(0).getId() + "; " +
                        bids.get(0).getName() + "; " +
                        bids.get(0).getDateOfBirth());
            } else {
                System.out.println("bidsCallback" + "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<BidFromServer>> call, Throwable t) {
            System.out.println("onFailure");
            t.printStackTrace();
        }
    };
}
