package com.yaelbottwitter.twitterapp.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyCredentials {
    private static final String clientID = "";
    private static final String clientSecret = "";

    public static String getClientID() {
        return clientID;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

}
