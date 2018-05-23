package com.innovation.tweetreader.twitter;

import com.google.gson.annotations.SerializedName;
import com.innovation.tweetreader.config.domain.Credentials;
import feign.Body;
import feign.Headers;
import feign.RequestLine;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;

import static com.innovation.tweetreader.config.reader.JsonConfigReader.readConfig;
import static feign.Feign.builder;

public class BearerTokenRetriever {
    private static final Credentials credentials = readConfig("credentials.json", Credentials.class);

    private BearerTokenRetriever() {

    }

    public BearerToken bearerToken() {
        return builder()
                .decoder(new GsonDecoder())
                .requestInterceptor(new BasicAuthRequestInterceptor(credentials.consumerKey(), credentials.consumerSecret()))
                .target(Token.class, "https://api.twitter.com")
                .bearerToken();
    }

    public static BearerTokenRetriever bearerTokenRetriever() {
        return new BearerTokenRetriever();
    }

    private interface Token {
        @RequestLine("POST /oauth2/token")
        @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
        @Body("grant_type=client_credentials")
        BearerToken bearerToken();
    }

    public static class BearerToken {
        @SerializedName("token_type")
        String tokenType;
        @SerializedName("access_token")
        String accessToken;

        public String tokenType() {
            return tokenType;
        }

        public String accessToken() {
            return accessToken;
        }
    }
}
