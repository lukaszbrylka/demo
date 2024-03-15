package com.demoqa.qa.tests.api.endpoints;

import com.demoqa.qa.tests.api.payload.Account;
import com.demoqa.qa.tests.api.payload.BookStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreEndpoint {

    public static Response readBooks() {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(Route.BookStore.getBooks);
    }

    public static Response readBook(BookStore payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .param("ISBN", payload.getISBN())
                .get(Route.BookStore.getBook);
    }

    public static Response createBooks(Account accountPayload, BookStore bookstorePayload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accountPayload.getToken())
                .body("{ \"userId\": \"" + accountPayload.getUserId() + "\", " +
                        "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookstorePayload.getISBN() + "\" } ]}")
                .when()
                .post(Route.BookStore.postBooks);

    }


    public static Response updateBooks(Account accountPayload, BookStore bookstorePayload, String newIsbnIndex) {
        String oldIsbn = bookstorePayload.getISBN();
        bookstorePayload.setISBN(newIsbnIndex);
        String newIsbn = bookstorePayload.getISBN();

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accountPayload.getToken())
                .pathParam("ISBN", oldIsbn)
                .body("{ \"userId\": \"" + accountPayload.getUserId() + "\", " +
                        "\"isbn\": \"" + newIsbn + "\" }")
                .when()
                .put(Route.BookStore.putBooks);
    }


    public static Response deleteBook(Account accountPayload, BookStore bookstorePayload) {
        String requestBody = "{ \"isbn\": \"" + bookstorePayload.getISBN() + "\", \"userId\": \"" + accountPayload.getUserId() + "\" }";

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accountPayload.getToken())
                .body(requestBody)
                .when()
                .delete(Route.BookStore.deleteBook);
    }

    public static Response deleteBooks(Account accountPayload, BookStore bookstorePayload) {

        return given()
                .header("Authorization", "Bearer " + accountPayload.getToken())
                .queryParam("UserId", accountPayload.getUserId())
                .when()
                .delete(Route.BookStore.deleteBooks);
    }


}
