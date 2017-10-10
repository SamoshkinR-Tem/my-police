package artsam.com.mypolice.models;

/**
 * Created by Artem on 09.10.17.
 */

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    private Long id;
    @SerializedName("senderName")
    private String senderName;
    @SerializedName("text")
    private String text;
//    @SerializedName("timeOfUpdate")
//    private String timeOfUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public String getTimeOfUpdate() {
//        return timeOfUpdate;
//    }

//    public void setTimeOfUpdate(String timeOfUpdate) {
//        this.timeOfUpdate = timeOfUpdate;
//    }

}