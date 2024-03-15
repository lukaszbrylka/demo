package com.demoqa.qa.tests.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.demoqa.qa.tests.api.payload.Account;


import static io.restassured.RestAssured.given;

public class AccountEndpoint {

    public static Response createAccount(Account payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Route.Account.postUser);

    }

    public static Response generateAccountToken(Account payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Route.Account.postGenerateToken);
    }

    public static Response accountAuthorized(Account payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Route.Account.postAuthorized);
    }

    public static Response readAccount(Account payload) {

        return given()
                .header("Authorization", "Bearer " + payload.getToken())
                .pathParam("UUID", payload.getUserId())
                .when()
                .get(Route.Account.getUser);

    }

    public static Response deleteAccount(Account payload) {

        return given()
                .header("Authorization", "Bearer " + payload.getToken())
                .pathParam("UUID", payload.getUserId())
                .when()
                .delete(Route.Account.deleteUser);

    }
}
