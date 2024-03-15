package com.demoqa.qa.tests.api.testCases;


import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.qa.tests.api.endpoints.AccountEndpoint;
import com.demoqa.qa.tests.api.payload.Account;

import static org.testng.Assert.*;

public class AccountTest {

    Faker fakeData;
    Account accountPayload;

    public Logger logger;

    @BeforeClass
    public void setupData() {
        fakeData = new Faker();
        accountPayload = new Account();
        accountPayload.setUserName(fakeData.name().username());
        accountPayload.setPassword(fakeData.internet().password(8, 15, true, true, true) + "Qw0!");

        logger = LogManager.getLogger(this.getClass());
        logger.info("\t *** TEST DATA *** \n \tUsername: " + accountPayload.getUserName() + ", Password: " + accountPayload.getPassword() + "\n");

    }

    @Test(priority = 1)
    public void testPostAccount() {
        Response response = AccountEndpoint.createAccount(accountPayload);
        response.then().log().all();


        logger.info("*** Creating account ***");
        logger.info("*** Response :" + response.asString() + "***");

        String jsonString = response.asString();
        String userId = JsonPath.from(jsonString).get("userID");

        accountPayload.setUserId(userId);

        assertEquals(response.getStatusCode(), 201);
        logger.info("*** Account created ***");


    }

    @Test(priority = 2)
    public void testGenerateToken() {
        Response response = AccountEndpoint.generateAccountToken(accountPayload);
        response.then().log().all();

        String jsonString = response.asString();

        String token = JsonPath.from(jsonString).get("token");
        String status = JsonPath.from(jsonString).get("status");

        logger.info("*** Generating token ***");
        assertNotNull(token);
        accountPayload.setToken(token);
        logger.info("*** Token: " + token + " ***");

        assertEquals(status, "Success");
        assertEquals(response.getStatusCode(), 200);
        logger.info("*** Token generated ***");


    }

    @Test(priority = 3)
    public void testPostAccountAuthorized() {
        Response response = AccountEndpoint.accountAuthorized(accountPayload);
        response.then().log().all();
        String jsonString = response.asString();

        logger.info("*** Authorizing account ***");
        assertEquals(jsonString, "true");
        assertEquals(response.getStatusCode(), 200);
        logger.info("*** Account authorized ***");

    }

    @Test(priority = 4)
    public void testGetAccount() {
        Response response = AccountEndpoint.readAccount(accountPayload);
        response.then().log().all();
        logger.info(response.asString());

        logger.info("*** Reading account info ***");
        assertEquals(response.getStatusCode(), 200);
        logger.info("*** Account info displayed ***");

    }

    @Test(priority = 5)
    public void testDeleteAccount() {
        Response response = AccountEndpoint.deleteAccount(accountPayload);
        response.then().log().all();

        logger.info("*** Deleting account ***");
        assertEquals(response.getStatusCode(), 204);
        logger.info("*** Account deleted ***");

    }


}
