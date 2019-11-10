package com.yaelbottwitter.twitterapp.twitter;

public class ParseString {

    public static void main(String[] args) {

        // Example of how to replace a the hashtag in twitter app to nothing.
        String str = "This is a string with a hashtag in it #cookies";
        str = str.replace("#cookies", ""); // removes #cookies
        System.out.println(str);
    }
}