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

    public Map<String, String> sendPostRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders) {
        RequestSpecification postRequest = RestAssured.given();
        Response postResponse;

        for (Map<String, String> requestHeader: requestHeaders) {
            postRequest.header(requestHeader.get("header"), requestHeader.get("value"));
        }

        postRequest.body(requestMap);
        postResponse = postRequest.post(endpoint);

        return createResponseMap(postResponse);
    }

    public Map<String, String> sendPutRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders) {
        RequestSpecification putRequest = RestAssured.given();
        Response putResponse;

        for (Map<String, String> requestHeader: requestHeaders) {
            putRequest.header(requestHeader.get("header"), requestHeader.get("value"));
        }

        putRequest.body(requestMap);
        putResponse = putRequest.put(endpoint);

        return createResponseMap(putResponse);
    }

    public Map<String, String> sendPatchRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders) {
        RequestSpecification putRequest = RestAssured.given();
        Response putResponse;

        for (Map<String, String> requestHeader: requestHeaders) {
            putRequest.header(requestHeader.get("header"), requestHeader.get("value"));
        }

        putRequest.body(requestMap);
        putResponse = putRequest.patch(endpoint);

        return createResponseMap(putResponse);
    }

    public Map<String, String> sendDeleteRequest(String endpoint, String path) {
        RequestSpecification getRequest = RestAssured.given();
        Response getResponse;

        getResponse = getRequest.delete(endpoint + "/" + path);

        return createResponseMap(getResponse);
    }

    private Map<String, String> createResponseMap(Response response) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("response", response.getBody().asString());
        responseMap.put("statusCode", String.valueOf(response.getStatusCode()));

        return responseMap;
    }
}