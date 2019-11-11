package com.yaelbottwitter.twitterapp;

import com.yaelbottwitter.twitterapp.twitter.TwitterDriver;
import com.yaelbottwitter.twitterapp.twitter.Twitterer;
import com.yaelbottwitter.twitterapp.twitter.YaelBotTweet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterappApplication.class, args);
    }

}
