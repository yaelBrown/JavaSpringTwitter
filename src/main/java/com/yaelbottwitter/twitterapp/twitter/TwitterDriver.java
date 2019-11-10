package com.yaelbottwitter.twitterapp.twitter;

import twitter4j.TwitterException;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TwitterDriver {

    private static PrintStream consolePrint;

    public static void main(String[] args) throws TwitterException, IOException {

        Twitterer t = new Twitterer(consolePrint);
        String message = "h3llo 4mb3r";

//        t.tweetOut(message);

//        t.queryHandle("_yaelBrown");

//        t.saQuery("#djyaelbot");

        t.yaelBotQuery();

    }

}
