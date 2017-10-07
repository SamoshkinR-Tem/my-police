package artsam.com.mypolice.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Artem on 07.10.17.
 */

public class LostChildBid extends LostChild {

    @SerializedName("name")
    private String name;

    @SerializedName("dateOfBirth")
    private Date dateOfBirth;

    @SerializedName("gender")
    private Gender gender;

    @SerializedName("childDescription")
    private String childDescription;

    @SerializedName("region")
    private String region;

    @SerializedName("situationDescription")
    private String situationDescription;

//    @SerializedName("timeOfLoss")
//    private Date timeOfLoss;

    @SerializedName("status")
    private SearchStatus status;

    private enum Gender {
        FEMALE, MALE
    }

    private enum SearchStatus {
        SEARCHING, FOUND, REJECTED
    }
}
