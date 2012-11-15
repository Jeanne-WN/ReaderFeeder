package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;

public class BooksFactory {
    //TODO: move ApiKey to configuration
    public static final String googleApiKey = "AIzaSyA6DYGeBhzOeFMVoz0YJohpScLX0Eafc04";

    public static Books newBooks() {
        return new Books.Builder(new NetHttpTransport(), new JacksonFactory(), null)
                .setApplicationName("Google-BooksSample/1.0").setJsonHttpRequestInitializer(new GoogleKeyInitializer(googleApiKey))
                .build();
    }
}
