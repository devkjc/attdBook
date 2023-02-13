package com.toy.atddbook.controller;

import com.toy.atddbook.domain.Book;
import com.toy.atddbook.dto.BookRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @LocalServerPort
    private int port;

    protected RequestSpecification spec;

    @BeforeEach
    void setUpRestDocs() {
        this.spec = new RequestSpecBuilder()
                .setPort(port)
                .setAccept(MediaType.APPLICATION_JSON_VALUE)
                .setContentType("application/json")
                .build();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 책_목록_조회() {

        //when
        ExtractableResponse<Response> response = RestAssured.given(this.spec).log().all()
                .when().get("/book/list")
                .then().log().all().extract();
        //then
        assertHttpOK(response);
    }

    @Test
    void 책_조회() {

        long bookId = 1;

        //given

        //when
        ExtractableResponse<Response> response = RestAssured.given(this.spec).log().all()
                .when().get("/book/{bookId}", bookId)
                .then().log().all().extract();

        //then
        assertHttpOK(response);

    }

    @Test
    void 책_저장() {

        //given
        String bookName = "톰소여의 모험";
        Book 톰소여의_모험 = BookRequest.builder()
                .bookName(bookName)
                .build().toEntity();

        //when
        ExtractableResponse<Response> response =
                RestAssured.given(this.spec).body(톰소여의_모험).log().all()
                    .when().post("/book")
                    .then().log().all().extract();
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().jsonPath().getString("bookName")).isEqualTo(bookName);
    }

    @Test
    void 책_대여() {

        //given
        long bookId = 1;
        //when
        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                    .when().post("/book/rent/{bookId}", bookId)
                    .then().log().all().extract();
        //then
        assertHttpOK(response);
        assertThat(response.body().jsonPath().getString("isRent")).isEqualTo(false);
    }

    private static void assertHttpOK(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
