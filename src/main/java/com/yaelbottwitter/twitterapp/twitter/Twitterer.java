package com.yaelbottwitter.twitterapp.twitter;

import twitter4j.GeoLocation;       // jar found at http://twitter4j.org/en/index.html
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

public class Twitterer
{
    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;
    private List<Status> oldStatuses;

    private List<YaelBotTweet> ybStatuses;
    private List<YaelBotTweet> oldybStatuses;

    private Status s;


    public Twitterer(PrintStream console)
    {
        // Makes an instance of Twitter - this is re-useable and thread safe.
        // Connects to Twitter and performs authorizations.
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
        ybStatuses = new ArrayList<YaelBotTweet>();
    }


      /**
       * This method tweets a given message.
//     * @param string  a message you wish to Tweet out
//     */
    public void tweetOut(String message) throws TwitterException, IOException
    {
        // Updates twitter status
        Status status = twitter.updateStatus(message);
        System.out.println("Successfully updated status to: " + status.getText());
    }

    /**
     * This method queries the tweets of a particular user's handle.
//     * @param String  the Twitter handle (username) without the @sign
     */
    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws TwitterException, IOException
    {
        statuses.clear();
        fetchTweets(handle);
        int counter = statuses.size();
        while(counter > 0) {
            counter--;
            System.out.println("Tweet #" + counter + ": " + statuses.get(counter).getText());
        }
    }

    /**
     * This helper method fetches the most recent 2,000 tweets of a particular user's handle and
     * stores them in an arrayList of Status objects.  Populates statuses.
//     * @param String  the Twitter handle (username) without the @sign
     */
    private void fetchTweets(String handle) throws TwitterException, IOException
    {
        Paging page = new Paging(1,200);
        int p = 1;
        while (p <= 10) {
            page.setPage(p);
            statuses.addAll(twitter.getUserTimeline(handle, page));
            p++;
        }
    }

    /**
     * This method finds the last 100 queries in the San Antonio area since yesterday.
     * Lat/Long for San Antonio is 29.4241° N, 98.4936° W (west is negative.)
     * @param searchTerm the term to search for.
     */
    public void saQuery (String searchTerm)
    {
        Query query = new Query(searchTerm);
        query.setCount(100);
        query.setGeoCode(new GeoLocation(29.4241, -98.4936), 20, Query.MILES);
        query.setSince("2019-11-09");

        try {
            QueryResult result = twitter.search(query);
            int counter = 0;
            System.out.println("Count: " + result.getTweets().size());
            for (Status tweet: result.getTweets()) {
                counter++;
                System.out.println("Tweet #" + counter + ": @" + tweet.getUser().getName() + "tweeted: " + tweet.getText());
            }
        } catch(TwitterException e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }
    }

    /***
     * Used for yaelBot application.
     */
    public void yaelBotQuery () {
        // move old data for comparison later
        oldStatuses = statuses;
        oldybStatuses = ybStatuses;

        // Clear the statuses to get new ones
        statuses.clear();
        ybStatuses.clear();

        // Define the query value
        Query q = new Query("#djyaelbot");

        // Perform check of query
        try {
            QueryResult res = twitter.search(q);
            q.setCount(100);
            System.out.println("Found " + res.getCount() + " tweets!");

            int count = 0;
            for (Status tweet: res.getTweets()) {
//                System.out.println("Tweet #" + 1 + ": @" + tweet.getUser().getName() + " tweeted: " + tweet.getText());
                ybStatuses.add(count, new YaelBotTweet(tweet.getId(), tweet.getUser().getName(), tweet.getText(), tweet.getCreatedAt()));
                count++;
            }

            System.out.println("ybStatuses.toString() = " + ybStatuses.toString());
        } catch(TwitterException e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }
    }

    public static void yaelBotQueryApplication() {
        System.out.println("YaelBot application started.");
    }

}
