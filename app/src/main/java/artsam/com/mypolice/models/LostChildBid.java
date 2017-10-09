package artsam.com.mypolice.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Artem on 07.10.17.
 */

public class LostChildBid {

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getChildDescription() {
        return childDescription;
    }

    public void setChildDescription(String childDescription) {
        this.childDescription = childDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSituationDescription() {
        return situationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }

    public SearchStatus getStatus() {
        return status;
    }

    public void setStatus(SearchStatus status) {
        this.status = status;
    }


    private enum Gender {
        FEMALE, MALE
    }

    private enum SearchStatus {
        SEARCHING, FOUND, REJECTED
    }
}
