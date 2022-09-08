package com.codeway.apiTest;

import com.codeway.apiClass.service.BaseService;
import com.codeway.apiClass.spec.ResponseSpec;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ReqresApiTest extends BaseService {

    @Test(description="Test Case 1 - Response Code Test")
    public void shouldResponseCodeOk(){

        Map<String, Object> byGetParams = requestMaps.getUserListMap();
        Response getUserService = getUsersService.get(byGetParams, ResponseSpec.checkStatusCodeOkey());

        JsonPath jsonPath = getUserService.jsonPath().prettyPeek();

        assertEquals(getUserService.getStatusCode(),200);

    }

    @Test(description="Test Case 2 - Response Body Last Name test")
    public void shouldResponseBodyContainsDesiredLastName(){

        Map<String, Object> byGetParams = requestMaps.getUserListMap();
        Response getUserService = getUsersService.get(byGetParams, ResponseSpec.checkStatusCodeOkey());

        JsonPath jsonPath = getUserService.jsonPath().setRootPath("data").prettyPeek();
        List<String> lastNameList = jsonPath.getList("last_name");
        String lastName = "";

        for(int i=0;i< lastNameList.size();i++){
            if (lastNameList.contains("Holt")) {
                lastName = "Holt";
                break;
            }
        }

        assertThat(lastName, Matchers.is("Holt"));
    }

    @Test(description="Test Case 3 - Total Number of data test")
    public void shouldResponseBodyContainsDesiredNumberOfData(){

        Map<String, Object> byGetParams = requestMaps.getUserListMap();
        Response getUserService = getUsersService.get(byGetParams, ResponseSpec.checkStatusCodeOkey());

        JsonPath jsonPath = getUserService.jsonPath().prettyPeek();
        int totalData = jsonPath.getInt("total");

        assertThat(totalData, Matchers.is(12));
    }

    @Test(description="Test Case 4 - Response Time Test")
    public void shouldResponseTimeOk(){

        Map<String, Object> byGetParams = requestMaps.getUserListMap();
        Response getUserService = getUsersService.get(byGetParams, ResponseSpec.checkStatusCodeOkey());

        JsonPath jsonPath = getUserService.jsonPath().prettyPeek();
        ValidatableResponse validatableResponse = getUserService.then();

        validatableResponse.time(Matchers.lessThanOrEqualTo(100L));

    }

    @Test(description="Test Case 5 - Response Size Test")
    public void shouldResponseSizeOk() {

        Map<String, Object> byGetParams = requestMaps.getUserListMap();
        Response getUserService = getUsersService.get(byGetParams, ResponseSpec.checkStatusCodeOkey());

        JsonPath jsonPath = getUserService.jsonPath().prettyPeek();
        int size = jsonPath.getList("data").size();

        assertThat(size,Matchers.lessThanOrEqualTo(2048));
    }

}
