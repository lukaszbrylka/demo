package com.demoqa.qa.tests.api.testCases;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.qa.tests.api.endpoints.AccountEndpoint;
import com.demoqa.qa.tests.api.endpoints.BookStoreEndpoint;
import com.demoqa.qa.tests.api.payload.Account;
import com.demoqa.qa.tests.api.payload.BookStore;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;


public class BookStoreTest {
    Faker fakeData;

    Account accountPayload;
    BookStore bookStorePayload;

    String token;

    String userId;

    String code;

    String message;

    String userID;

    Logger logger;

    String randomISBN;
    String newRandomISBN;

    @BeforeClass
    public void setupData() {
        fakeData = new Faker();

        setupAccount();
        setupBookStore();
    }


    private void setupAccount() {
        accountPayload = new Account();
        accountPayload.setUserName(fakeData.name().username());
        accountPayload.setPassword(fakeData.internet().password(8, 15, true, true, true) + "Qw0!");

        userId = JsonPath.from(AccountEndpoint.createAccount(accountPayload).asString()).get("userID");
        token = JsonPath.from(AccountEndpoint.generateAccountToken(accountPayload).asString()).get("token");
        accountPayload.setUserId(userId);
        accountPayload.setToken(token);

        logger = LogManager.getLogger(this.getClass());
        logger.info("\t *** TEST DATA *** \n \tUsername: " + accountPayload.getUserName() + ", Password: " + accountPayload.getPassword() + ", UserID: " + accountPayload.getUserId() + "\n token:" + accountPayload.getToken());

    }

    private void setupBookStore() {
        bookStorePayload = new BookStore();

        List<String> isbns = JsonPath.from(BookStoreEndpoint.readBooks().asString()).getList("books.isbn");
        logger.info("\t Number of books: " + isbns.size() + "\n");
        for (String isbn : isbns) {
            logger.info("\t ISBN: " + isbn);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(isbns.size());
        randomISBN = isbns.get(randomIndex);

        int newRandomIndex;
        do {
            newRandomIndex = random.nextInt(isbns.size());
        } while (newRandomIndex == randomIndex);

        newRandomISBN = isbns.get(newRandomIndex);

        bookStorePayload.setISBN(randomISBN);
        bookStorePayload.setTitle(String.valueOf(fakeData.book()));
        bookStorePayload.setSubTitle(String.valueOf(fakeData.lorem()));
        bookStorePayload.setAuthor(String.valueOf(fakeData.artist()));
        bookStorePayload.setPublished(fakeData.date().toString());
        bookStorePayload.setPublisher(fakeData.name().lastName());
        bookStorePayload.setPages(fakeData.number().numberBetween(100, 1000));
        bookStorePayload.setDescription(fakeData.superhero().descriptor());
        bookStorePayload.setWebsite(fakeData.internet().domainWord());
    }

    @Test(priority = 1)
    public void testGetBooks() {
        Response response = BookStoreEndpoint.readBooks();
        response.then().log().all();
        logger.info("*** Reading books info ***");

        logger.info(response.asString());

        assertEquals(response.getStatusCode(), 200);
        logger.info("*** Books info displayed ***");

    }

    @Test(priority = 2)
    public void testGetBook() {
        Response response = BookStoreEndpoint.readBook(bookStorePayload);
        response.then().log().all();
        logger.info("*** Reading book info  ***");

        logger.info(response.asString());

        assertEquals(response.getStatusCode(), 200);
        logger.info("*** Book info displayed ***");

    }

    @Test(priority = 3)
    public void testAddBook() {
        List<BookStore.ISBN> isbns = this.bookStorePayload.collectionOfIsbns;

        Response response = BookStoreEndpoint.createBooks(accountPayload, bookStorePayload);
        response.then().log().all();
        logger.info("*** Adding new book ***");

        logger.info(response.asString());

        assertEquals(response.getStatusCode(), 201);
        logger.info("*** New book added ***");

    }

    @Test(priority = 4)
    public void testPutBooks() {
        Response response = BookStoreEndpoint.updateBooks(accountPayload, bookStorePayload, newRandomISBN);
        response.then().log().all();
        logger.info("*** Updating book info ***");

        logger.info(response.asString());

        assertEquals(response.getStatusCode(), 200);
        logger.info("*** Book info updated ***");

    }

    @Test(priority = 5)
    public void testDeleteBook() {
        Response response = BookStoreEndpoint.deleteBook(accountPayload, bookStorePayload);
        response.then().log().all();

        logger.info("*** Deleting book ***");
        logger.info(response.asString());

        assertEquals(response.getStatusCode(), 204);
        logger.info("*** Book deleted ***");

    }

    @Test(priority = 6)
    public void testDeleteBooks() {
        Response response = BookStoreEndpoint.deleteBooks(accountPayload, bookStorePayload);
        response.then().log().all();

        logger.info("*** Deleting books ***");
        assertEquals(response.getStatusCode(), 204);
        logger.info("*** Books deleted ***");

    }


}



