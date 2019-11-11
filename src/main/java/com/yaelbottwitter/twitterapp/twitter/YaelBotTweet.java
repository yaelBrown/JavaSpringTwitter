package com.yaelbottwitter.twitterapp.twitter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Date;

public class YaelBotTweet {

    private String userId;
    private String userName;
    private String ybTweetText;
    private Date ybTweetDate;

    public YaelBotTweet(Long userId, String userName, String ybTweetText, Date ybTweetDate) {
        this.userId = userId.toString();
        this.userName = userName;
        this.ybTweetText = ybTweetText.replace("#djyaelbot", "");
        this.ybTweetDate = ybTweetDate;
    }

    @Override
    public String toString() {
        return "YaelBotTweet{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", ybTweetText='" + ybTweetText + '\'' +
                ", ybTweetDate=" + ybTweetDate +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getYbTweetText() {
        return ybTweetText;
    }

    public void setYbTweetText(String ybTweetText) {
        this.ybTweetText = ybTweetText;
    }

    public Date getYbTweetDate() {
        return ybTweetDate;
    }

    public void setYbTweetDate(Date ybTweetDate) {
        this.ybTweetDate = ybTweetDate;
    }

    public JsonObject toJSON() {
        JsonObject output = new JsonObject();
        output.addProperty("userId", this.userId);
        output.addProperty("username", this.userName);
        output.addProperty("tweet", this.ybTweetText);
        output.addProperty("date", this.ybTweetDate.toString());
        return output;
    }


}
