package com.yaelbottwitter.twitterapp.spotify;

import com.wrapper.spotify.SpotifyHttpManager;

import java.net.URI;

public class SpotifyCredentials {
    private static final String clientID = "2f655dc667c548f290ef069619a9ce1e";
    private static final String clientSecret = "0dae77e1a7ff4f8ba2215a3f29c27748";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

    public static URI getRedirectUri() {
        return redirectUri;
    }

    public static String getClientID() {
        return clientID;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

}
