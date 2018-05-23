package com.innovation.tweetreader.config.domain;

import com.google.gson.annotations.SerializedName;

public class Credentials {
    @SerializedName("consumer_key")
    String consumerKey;

    @SerializedName("consumer_secret")
    String consumerSecret;

    public String consumerKey() {
        return consumerKey;
    }

    public String consumerSecret() {
        return consumerSecret;
    }
}