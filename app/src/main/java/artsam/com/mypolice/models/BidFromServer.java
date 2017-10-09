package artsam.com.mypolice.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Artem on 07.10.17.
 */

public class BidFromServer extends LostChildBid {

    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @SerializedName("timeOfRequest")
//    private Date timeOfRequest;
}
