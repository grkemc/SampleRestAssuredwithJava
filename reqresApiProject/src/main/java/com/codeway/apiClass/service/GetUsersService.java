package com.codeway.apiClass.service;

import com.codeway.apiClass.spec.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static com.codeway.apiClass.helper.Constans.*;

public class GetUsersService extends RequestSpec {

    public GetUsersService(){ super (BASE_URL + USERS_PATH);}

    public Response get(Map<String, Object> params, ResponseSpecification responseSpecification) {
        return RestAssured.given()
                .spec(super.getRequestSpecification())
                .queryParams(params)
                .get()
                .then()
                .spec(responseSpecification)
                .extract().response();
    }
}
