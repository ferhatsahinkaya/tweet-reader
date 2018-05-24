package com.innovation.tweetreader;

import com.innovation.tweetreader.config.domain.Credentials;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.google.common.collect.Lists.newArrayList;
import static com.innovation.tweetreader.config.reader.JsonConfigReader.readConfig;
import static com.twitter.hbc.core.Constants.STREAM_HOST;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(100000);

        Credentials credentials = readConfig("credentials.json", Credentials.class);

        Authentication authentication = new OAuth1(
                credentials.consumerKey(),
                credentials.consumerSecret(),
                credentials.token(),
                credentials.tokenSecret());

        Client client = new ClientBuilder()
                .name("tweet-reader-client")
                .hosts(STREAM_HOST)
                .authentication(authentication)
                .endpoint(new StatusesFilterEndpoint().trackTerms(newArrayList("twitter", "api")))
                .processor(new StringDelimitedProcessor(queue))
                .build();

        client.connect();

        while (!client.isDone()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        client.stop();
    }
}
