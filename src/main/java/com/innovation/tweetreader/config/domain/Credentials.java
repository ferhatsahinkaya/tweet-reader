package com.innovation.tweetreader.config.domain;

import com.google.gson.annotations.SerializedName;

public class Credentials {
    @SerializedName("consumer_key")
    String consumerKey;

    @SerializedName("consumer_secret")
    String consumerSecret;

    @SerializedName("token")
    String token;

    @SerializedName("token_secret")
    String tokenSecret;

    public String consumerKey() {
        return consumerKey;
    }

    public String consumerSecret() {
        return consumerSecret;
    }

    public String token() {
        return token;
    }

    public String tokenSecret() {
        return tokenSecret;
    }
}