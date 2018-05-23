package com.innovation.tweetreader;

import com.innovation.tweetreader.twitter.BearerTokenRetriever.BearerToken;

import static com.innovation.tweetreader.twitter.BearerTokenRetriever.bearerTokenRetriever;

public class Main {
    public static void main(String[] args) {
        final BearerToken bearerToken = bearerTokenRetriever().bearerToken();
        System.out.println("BearerToken : " + bearerToken.tokenType() + ", AccessToken : " + bearerToken.accessToken());
    }
}
