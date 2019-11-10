package com.yaelbottwitter.twitterapp.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.concurrent.*;

import java.net.URI;

public class Spotifier {

    // For all requests an access token is needed
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(SpotifyCredentials.getClientID())
            .setClientSecret(SpotifyCredentials.getClientSecret())
            .setRedirectUri(URI.create("/"))
            .build();

    // Query for searching the Spotify Api
    private static String q;

    public static String getQ() {
        return q;
    }

    public static void setQ(String q) {
        Spotifier.q = q;
    }

    private static SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q).build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    public static void clientCredentials_Sync() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void clientCredentials_Async() {
        try {
            final CompletableFuture<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final ClientCredentials clientCredentials = clientCredentialsFuture.join();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    // Method for searching spotify tracks
    public static void searchTracks_Sync() {
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();
            System.out.println("Total: " + trackPaging.getTotal());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchTracks_Async() {
        try {
            final CompletableFuture<Paging<Track>> pagingFuture = searchTracksRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<Track> trackPaging = pagingFuture.join();

            System.out.println("Total: " + trackPaging.getTotal());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

//    // Create a request object with the optional parameter "market"
//    final GetSomethingRequest getSomethingRequest = spotifyApi.getSomething("qKRpDADUKrFeKhFHDMdfcu")
//            .market(CountryCode.US)
//            .build();
//
//    void getSomething_Sync() {
//        try {
//            // Execute the request synchronous
//            final Something something = getSomethingRequest.execute();
//
//            // Print something's name
//            System.out.println("Name: " + something.getName());
//        } catch (Exception e) {
//            System.out.println("Something went wrong!\n" + e.getMessage());
//        }
//    }
//
//    void getSomething_Async() {
//        try {
//            // Execute the request asynchronous
//            final Future<Something> somethingFuture = getSomethingRequest.executeAsync();
//
//            // Do other things...
//
//            // Wait for the request to complete
//            final Something something = somethingFuture.get();
//
//            // Print something's name
//            System.out.println("Name: " + something.getName());
//        } catch (Exception e) {
//            System.out.println("Something went wrong!\n" + e.getMessage());
//        }
//    }

    public static void main(String[] args) {

        Spotifier spot = new Spotifier();

        spot.setQ("Snoop Dogg Gin and Juice");

        spot.searchTracks_Sync();

    }


}
