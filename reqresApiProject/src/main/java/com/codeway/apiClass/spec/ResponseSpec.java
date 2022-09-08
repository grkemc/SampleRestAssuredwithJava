package com.codeway.apiClass.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class ResponseSpec {

    public static ResponseSpecification checkStatusCodeOkey() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
}
