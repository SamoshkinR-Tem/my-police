package artsam.com.mypolice;

import java.util.List;

import artsam.com.mypolice.models.LostChild;
import artsam.com.mypolice.models.LostChildBid;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Artem on 07.10.17.
 */

public interface MyPoliceClient {
    static final String BASE_URL = "http://35.157.117.160:80";

    @GET("/search")
    Call<List<LostChild>> getLostChildBids(
            @Header("Authorization") String credentials,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @POST("/search")
    Call<LostChildBid> postLostChildBid (@Body LostChildBid lostChildBid);

    @PUT("/search/{searchId}")
    Call<LostChildBid> updateBidById(
            @Path("searchId") Long searchId,
            @Body LostChildBid lostChildBid);

    @DELETE("/search/{searchId}")
    Call<Response> deleteBidById(@Path("searchId") Long deleteId);
}
