package com.deloitte.qa.commons.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

public class RequestApi {

    public Map<String, String> sendGetRequest(String endpoint) {
        RequestSpecification getRequest = RestAssured.given();
        Response getResponse;

        getResponse = getRequest.get(endpoint);

        return createResponseMap(getResponse);
    }

    public Map<String, String> sendGetRequest(String endpoint, String path) {
        RequestSpecification getRequest = RestAssured.given();
        Response getResponse;

        getResponse = getRequest.get(endpoint + "/" + path);

        return createResponseMap(getResponse);
    }

    public Map<String, String> sendCreateRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders) {
        RequestSpecification createRequest = RestAssured.given();
        Response createResponse;

        for (Map<String, String> requestHeader: requestHeaders) {
            createRequest.header(requestHeader.get("header"), requestHeader.get("value"));
        }

        createRequest.body(requestMap);
        createResponse = createRequest.post(endpoint);

        return createResponseMap(createResponse);
    }

    private Map<String, String> createResponseMap(Response response) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("response", response.getBody().asString());
        responseMap.put("statusCode", String.valueOf(response.getStatusCode()));

        return responseMap;
    }
}