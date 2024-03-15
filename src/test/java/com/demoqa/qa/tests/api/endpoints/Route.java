package com.demoqa.qa.tests.api.endpoints;


public class Route {
    public static String baseUrl = "https://demoqa.com";
    private static final String version = "/v1";

    public static class Account {
        private static final String account = "/Account";
        static String accountUrl = baseUrl + account + version;
        static final String postUser = accountUrl + "/User";
        static final String postAuthorized = accountUrl + "/Authorized";
        static final String postGenerateToken = accountUrl + "/GenerateToken";
        static final String deleteUser = accountUrl + "/User/{UUID}";
        static final String getUser = accountUrl + "/User/{UUID}";

    }

    public static class BookStore {

        private static final String bookStore = "/BookStore";

        static String bookStoreUrl = baseUrl + bookStore + version;

        static final String getBook = bookStoreUrl + "/Book";
        static final String deleteBook = bookStoreUrl + "/Book";
        static final String getBooks = bookStoreUrl + "/Books";
        static final String postBooks = bookStoreUrl + "/Books";
        static final String putBooks = bookStoreUrl + "/Books/{ISBN}";
        static final String deleteBooks = bookStoreUrl + "/Books/";


    }
}
