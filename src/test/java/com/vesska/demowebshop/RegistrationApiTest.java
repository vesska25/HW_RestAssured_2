package com.vesska.demowebshop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegistrationApiTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    @Tag("API")
    @DisplayName("Newsletter subscription API test")
    void subscriptionToNewsletterTest() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("email=testsub@mailinator.com")
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up!" +
                        " A verification email has been sent. We appreciate your interest."));
    }

    @Test
    @Tag("API")
    @DisplayName("Community poll unregistered voting test")
    void voteUnregisteredTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("pollAnswerId=2")
                .when()
                .post("/poll/vote")
                .then()
                .log().all()
                .statusCode(200)
                .body("error", is("Only registered users can vote."));
    }



}
