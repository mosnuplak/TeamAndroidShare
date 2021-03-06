package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 7/8/2560.
 */

public class TitleDao {
    @SerializedName("headTopic")
    private String head;

    @SerializedName("description")
    private String description;

    @SerializedName("categoryName")
    private String category;

    @SerializedName("link")
    private String link;

    @SerializedName("score")
    private int score;

    @SerializedName("poster")
    private String poster;

    @SerializedName("userId")
    private String userId;

    @SerializedName("totalViewer")
    private String totalViewer;

    @SerializedName("topicId")
    private String topicId;

    @SerializedName("totalLike")
    private String totalLike;

    @SerializedName("status")
    private Boolean status;

    @SerializedName("imageUrl")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TitleDao(){

    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(String totalLike) {
        this.totalLike = totalLike;
    }


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTotalViewer() {
        return totalViewer;
    }

    public void setTotalViewer(String totalViewer) {
        this.totalViewer = totalViewer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @SerializedName("createDate")

    private  long createDate;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

}
