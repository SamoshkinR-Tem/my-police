package artsam.com.mypolice.client;

import java.util.List;

import artsam.com.mypolice.models.Comment;
import artsam.com.mypolice.models.BidFromServer;
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
    Call<List<BidFromServer>> getLostChildBids(@Query("offset") int offset,
                                               @Query("limit") int limit);

    @POST("/search")
    Call<Response> postLostChildBid(@Body LostChildBid lostChildBid);

    @PUT("/search/{searchId}")
    Call<Response> updateBidById(@Path("searchId") Long searchId,
                                 @Body LostChildBid lostChildBid);

    @DELETE("/search/{searchId}")
    Call<Response> deleteBidById(@Path("searchId") Long deleteId);

    @GET("/children/{searchId}/comments")
    Call<List<Comment>> getBidComments(@Path("searchId") Long bidId,
                                       @Query("offset") int offset,
                                       @Query("limit") int limit);

    @POST("/children/{searchId}/comments")
    Call<Response> postBidComment(@Path("searchId") Long bidId,
                                  @Body String commentText);

    @PUT("/children/{searchId}/comments/{commentId}")
    Call<Response> updateBidCommentById(@Path("searchId") Long bidId,
                                        @Path("commentId") Long commentId,
                                        @Body String commentText);

    @DELETE("/search/{searchId}/comments/{commentId}")
    Call<Response> deleteBidCommentById(@Path("searchId") Long bidId,
                                        @Path("commentId") Long commentId);
}

