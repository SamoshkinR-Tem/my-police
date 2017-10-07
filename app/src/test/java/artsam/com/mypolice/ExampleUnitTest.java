package artsam.com.mypolice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.List;

import artsam.com.mypolice.models.LostChild;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    MyPoliceClient myPoliceClient;

    @Test
    public void tryToGetLostChildrenBids() throws Exception {
        createMyPoliceAPI();
        String credentials = Credentials.basic("andrey", "and123and");
        System.out.println("Hi");

        myPoliceClient.getLostChildBids(credentials, 0, 2).enqueue(bidsCallback);
    }

    Callback<List<LostChild>> bidsCallback = new Callback<List<LostChild>>() {
        @Override
        public void onResponse(Call<List<LostChild>> call, Response<List<LostChild>> response) {
            System.out.println("onResponse");
            if (response.isSuccessful()) {
                List<LostChild> bids = response.body();
                System.out.println(bids);
            } else {
                System.out.println("bidsCallback" + "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<LostChild>> call, Throwable t) {
            System.out.println("onFailure");
            t.printStackTrace();
        }
    };

    private void createMyPoliceAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyPoliceClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myPoliceClient = retrofit.create(MyPoliceClient.class);
    }
}