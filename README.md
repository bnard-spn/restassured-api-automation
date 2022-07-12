# API Automation Framework

---------------------------

## Background 

This is an API Automation Framework coded in Java with **[RestAssured.io](https://github.com/rest-assured/rest-assured)**. 

The purpose of this automation framework is to assist QA Test Engineers in their test automation projects in sending and receiving API requests and responses, that they don't need to connect and learn a new API test framework in their automation project.

---------------------------

## Resources

Git Repo: https://github.com/bnard-spn/restassured-api-automation

Sample Cucumber Test Automation using this framework: https://github.com/bnard-spn/restassured-cucumber-api-project

Mock API: https://github.com/bnard-spn/js-express-api

---------------------------

## How to connect

The initial requirements for you to connect with this test automation framework is to have a test automation project coded in Java and uses Maven as a dependency management tool with a _pom.xml_ file in the test automation project. 

It is also possible to add this test automation framework to your project if you have Gradle as your dependency management tool, but it has not been tested yet.

Kindly follow the steps below if you meet the requirement above:

### 1. Cloning the Git Project

Go to the project folder where your test automation is stored and clone this git project at that same directory by using this command:

```
  git clone https://github.com/bnard-spn/restassured-api-automation.git
```

That will copy the git project in your local folder.

### 2. Build project

Go to your terminal and go to the directory where this automation framework is saved:

```
  cd <path_to_project_directory>/restassured-api-automation/
```

Once inside the project folder, you can build the project using this command:

```
  mvn install
```

The terminal should log this maven build similar to this:

![MavenBuild](src/test/resources/docs/MavenBuild.PNG)

This command will build the project and will create its _.jar_ file that can be used by other java projects, specifically your test automation project. 

### 3. Add Maven dependency 

In your test automation project, go to your _pom.xml_ file and put this code block in the dependency block:

```xml
<dependency>
    <groupId>com.deloitte.qa.commons</groupId>
    <artifactId>restassured-api-automation</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

After putting this in your _pom.xml_ file, you need to rebuild your project for the changes to take effect. If build is successful, you can now connect to the different public methods and classes of the automation framework.

> **Note:**
> 
> It is possible to add this API test automation git project as a maven dependency without cloning the git project itself to your local machine. This feature is still undergoing feasibility testing. This project will be updated once this feature is available.

---------------------------

## Features

### Helper Methods

---------------------------
#### RequestAPI class

This class contains helper java functions that you can use in your test automation to easily send API requests and get the response and status code for easy test validation.

User just need to instantiate the RequestAPI class in their test automation code same to the example below:

```java
import com.deloitte.qa.commons.helpers.RequestApi;

public class TestAutomationCode {
    private final RequestApi requestApi = new RequestApi();
}
```

> **Note:**
>
> A complete example will be given below the list of the available helper methods.

---------------------------

##### GET request

In the current version, there are two available GET request helper methods:

1. `sendGetRequest(String endpoint)`

Documentation:
```java
public Map<String, String> sendGetRequest(String endpoint) {
    RequestSpecification getRequest = RestAssured.given();
    Response getResponse;

    getResponse = getRequest.get(endpoint);

    return createResponseMap(getResponse);
}
```
This method accepts a string parameter to be used as the `endpoint` for sending the GET Request using RestAssured `RequestSpecification` object. The RestAssured `Response` object will store the response received from the API and the method will return a `Map<String, String>` object.

Usage:
```java
@Test
public void testForSendGetRequest() {
    String endpoint = "https://test-api.com/data";
    
    Map<String, String> responseMap = new HashMap<>();
    
    responseMap = requestApi.sendGetRequest(endpoint);
}
```

2. `sendGetRequest(String endpoint, String path)`

Documentation:
```java
public Map<String, String> sendGetRequest(String endpoint, String path) {
    RequestSpecification getRequest = RestAssured.given();
    Response getResponse;

    getResponse = getRequest.get(endpoint + "/" + path);

    return createResponseMap(getResponse);
}
```
This method accepts two parameters:
- `endpoint` - `String` datatype. To be used for sending the GET request.
- `path` - `String` datatype. This will hold the additional parameters that may be needed in the GET request like _path variables_ and/or _query parameters_.

Same as the other API helper methods, it returns a `Map<String, String>` object.

Usage:
```java
@Test
public void testForSendGetRequest(){
    String endpoint="https://test-api.com/data";
    String id = "id-123";
    String queryParams = "?includeDetails=true"
        
    Map<String, String> responseMap=new HashMap<>();
    
    responseMap = requestApi.sendGetRequest(endpoint, id + queryParams);
}
```

> **Note:**
>
> Please see the [guide]() below for reading the returned `responseMap` of the helper method.

---------------------------

##### POST, PUT, PATCH requests

In the current version, there is one available request helper method for each API method. Their usages are the same, and the only difference is the method to call.

1. `sendPostRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders)`
2. `sendPutRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders)`
3. `sendPatchRequest(String endpoint, Map<String, Object> requestMap, List<Map<String, String>> requestHeaders)`

Documentation:
```java
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
```

These methods accept three parameters:
 - `endpoint` - `String` datatype. To be used for sending the POST request.
 - `requestMap` - `Map<String, String>` datatype. This will hold the JSON request body for the POST request.
 - `requestHeaders` - `List<Map<String, String>>` datatype. A list of request headers to be added for the POST request.

These methods will iterate the list of `requestHeaders` and put it as a header in the `RequestSpecification` object. Then it will add the `requestMap` as the request body. Finally, it will send the post request using the `endpoint`. Same as the other API helper methods, it returns a `Map<String, String>` object.

> **Note:**
>
> Please see the [guide]() below for creating the `requestMap` and `requestHeaders` objects.

Usage:
```java
@Test
public void testForSendPostRequest() {
    String endpoint = "https://test-api.com/create";
    
    Map<String, String> requestMap = new HashMap<>();
    Map<String, String> responseMap = new HashMap<>();
    
    List<Map<String, String>> requestHeaders = new ArrayList<>();
    Map<String, String> headersMap = new HashMap<>();
    
    requestMap.put("id", "id-123");
    requestMap.put("name", "John Smith");
    
    headersMap.put("key", "Content-Type");
    headersMap.put("value", "application/json");
    requestHeaders.add(headersMap);
    
    responseMap = requestApi.sendPostRequest(endpoint, requestMap, requestHeaders);
}
```
> **Note:**
>
> Please see the [guide]() below for reading the returned `responseMap` of the helper method.

---------------------------

##### DELETE request

In the current version, there are two available DELETE request helper methods:

1. `sendDeleteRequest(String endpoint, String path)`

Documentation:
```java
public Map<String, String> sendDeleteRequest(String endpoint, String path) {
    RequestSpecification deleteRequest = RestAssured.given();
    Response deleteResponse;

    deleteResponse = deleteRequest.delete(endpoint + "/" + path);

    return createResponseMap(deleteResponse);
}
```
This method accepts two parameters:
- `endpoint` - `String` datatype. To be used for sending the GET request.
- `path` - `String` datatype. This will hold the additional parameters that may be needed in the DELETE request like an ID to be used for deleting the data.

Same as the other API helper methods, it returns a `Map<String, String>` object.

Usage:
```java
@Test
public void testForSendDeleteRequest(){
    String endpoint="https://test-api.com/delete";
    String id = "id-123";
        
    Map<String, String> responseMap = new HashMap<>();
    
    responseMap = requestApi.sendDeleteRequest(endpoint, id);
}
```
> **Note:**
>
> Please see the [guide]() below for reading the returned `responseMap` of the helper method.

2. `sendDeleteRequest(String endpoint, String path, List<Map<String, String>> requestHeaders)`

Documentation:
```java
public Map<String, String> sendDeleteRequest(String endpoint, String path, List<Map<String, String>> requestHeaders) {
    RequestSpecification deleteRequest = RestAssured.given();
    Response deleteResponse;

    if (!requestHeaders.isEmpty()) {
        for (Map<String, String> requestHeader: requestHeaders) {
            deleteRequest.header(requestHeader.get("header"), requestHeader.get("value"));
        }
    }

    deleteResponse = deleteRequest.delete(endpoint + "/" + path);

    return createResponseMap(deleteResponse);
}
```

This method accepts three parameters:
- `endpoint` - `String` datatype. To be used for sending the GET request.
- `path` - `String` datatype. This will hold the additional parameters that may be needed in the DELETE request like an ID to be used for deleting the data.
- `requestHeaders` - `List<Map<String, String>>` datatype. This will hold headers that may be needed for the DELETE request like the Authorization header to access the DELETE method.

This method will iterate the list of items in the `requestHeader` object and put it as headers in the `RequestSpecification` object. Same as the other API helper methods, it returns a `Map<String, String>` object.

> **Note:**
>
> Please see the [guide]() below for creating the `requestHeaders` object.

Usage:
```java
@Test
public void testForSendDeleteRequest(){
    String endpoint="https://test-api.com/delete";
    String id = "id-123";
        
    Map<String, String> responseMap = new HashMap<>();

    List<Map<String, String>> requestHeaders = new ArrayList<>();
    Map<String, String> headersMap = new HashMap<>();
    
    headersMap.put("header", "Authorization");
    headersMap.put("value", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
    requestHeaders.add(headersMap);
    
    responseMap = requestApi.sendDeleteRequest(endpoint, id, requestHeaders);
}
```
> **Note:**
>
> Please see the [guide]() below for reading the returned `responseMap` of the helper method.

---------------------------

##### Request Guide

###### Request Body

Some API requests like POST, PUT, and PATCH methods are requiring a request body when sending API requests. Usually, it is in a JSON formatted message. Like the example below:

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 24
}
```

The helper methods for POST, PUT, and PATCH discussed above are requiring users to pass the `requestMap` object with a `Map<String, Object>` datatype.

RestAssured.io can read a java `Map` object and convert it into a JSON object to be used as a request body in the API request.

When creating the `requestMap` object, you need to initialize a variable with a `Map<String, Object>` datatype. Constructing a Map object in java is similar to constructing a JSON message. You just need to provide the key-value pair.

In our JSON object example above, the `firstName` is the key, and `John` is the value. You can create the same JSON object by creating a Java Map object by following the example below:

```java
@Test
public void test() {
    Map<String, Object> requestMap = new HashMap<>();
    
    requestMap.put("firstName", "John");
    requestMap.put("lastName", "Smith");
    requestMap.put("age", 24);
}
```

The created `requestMap` can now be used for the needed parameter of the POST, PUT, and PATCH helper methods.

---------------------------

###### Request Headers

API requests usually requires headers on their requests. The helper methods accepts a `requestHeaders` parameter with a `List<Map<String, String>>` datatype. The helper method accepts a list of Map objects in order to support multiple request headers.

For a better representation, here's what a content of `requestHeaders` should look like:
```json
[
  {
    "header" : "Content-Type",
    "value": "application/json"
  },
  {
    "header": "Authorization",
    "value": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
  }
]
```

You need to create a Map object with two keys, `header` and `value`. The `header` key should contain the request header's name/key, and the `value` key will contain the header's value. After creating the map object, you need to add it in a list object as shown in the example below:

```java
@Test
public void test() {
    List<Map<String, String>> requestHeaders = new ArrayList<>();
    Map<String, Object> headersMap = new HashMap<>();
    
    headersMap.put("header", "Content-Type");
    headersMap.put("value", "application/json");
    requestHeaders.add(headersMap);
    
    headersMap = new HashMap<>();
    headersMap.put("header", "Authorization");
    headersMap.put("value", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
    requestHeaders.add(headersMap);
}
```
> **Note:**
>
> `headersMap` should contain the correct keys for it to be read by the helper methods, otherwise it would cause an error. It needs to have the `header` and `value` keys.

The created `requestHeaders` can now be used as for the needed parameter of the helper methods.

---------------------------

###### Response Map

The helper methods will return a `responseMap` object with a `Map<String, String>` datatype. It will contain the response body returned by the API and the response status code.

Documentation:
```java
private Map<String, String> createResponseMap(Response response) {
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("response", response.getBody().asString());
    responseMap.put("statusCode", String.valueOf(response.getStatusCode()));
    
    return responseMap;
}
```

Each helper method calls this private method inside the RequestApi class to return the `responseMap` object. From the RestAssured `Response` object, it gets the response body and status code and stores it into a Map object containing the `response` and `statusCode` keys that you can use in your test automation.

Usage:
```java
@Test
public void test() {
    Map<String, String> responseMap = requestApi.sendDeleteRequest(endpoint, id, requestHeaders);
    
    System.out.println(responseMap.get("response"));
    System.out.println(responseMap.get("statusCode"));
}
```

As same as accessing a Map of its keys, you can access the string values of the `response` and `statusCode` keys of the `responseMap`.

---------------------------

#### Assertions

This class contains helper functions to easily assert/validate test cases without the need to call the `Assertion` module of the `JUnit` library.

User just need to instantiate the Assertions class in their test automation code same to the example below:

```java
import com.deloitte.qa.commons.helpers.Assertions;

public class TestAutomationCode {
    private final Assertions assertion = new Assertions();
}
```

> **Note:**
>
> A complete example will be given below the list of the available helper methods.

---------------------------

##### Assert Methods

In the current version, there are three available assert helper methods. These methods call the JUnit Assertion module so your test automation project doesn't have to.

1. `assertIfTrue(boolean condition)`
2. `assertIfEquals(Object expected, Object actual)`
3. `assertIfNotNull(Object obj)`

Documentation:
```java
public static void assertIfTrue(boolean condition) {
    assertTrue(condition);
}

public static void assertIfEquals(Object expected, Object actual) {
    assertEquals(expected, actual);
}

public static void assertIfNotNull(Object obj) {
    assertNotNull(obj);
}
```

These methods accept the same parameters needed for each JUnit assert functions. The same with the regular assert functions it will return an AssertionError if the assertion criteria has not been met.

Usage:
```java
private final Assertions assertion = new Assertions();

@Test
public void test() {
    String word = "Test";
    assertion.assertIfTrue(word == "Test");
    
    assertion.assertIfEquals(word, "Test");
    
    assertion.assertIfNotNull(word);
}
```

---------------------------

##### Status Code Validation

Inside the Assertions class, there is a helper method called `validateStatusCode` that will take a `status` parameter that usually comes from response bodies and will check if the response status code you are validating is correct for that status.

Documentation:
```java
public static void validateStatusCode(String status, String code) {
    switch (status) {
        case "SUCCESSFUL" -> assertEquals("200", code);
        case "CREATED" -> assertEquals("201", code);
        case "NOT_FOUND" -> assertEquals("404", code);
        case "BAD_REQUEST" -> assertEquals("400", code);
        case "UNAUTHORIZED" -> assertEquals("401", code);
        case "ACCESS_FORBIDDEN" -> assertEquals("403", code);
        case "SERVICE_UNAVAILABLE" -> assertEquals("500", code);
    }
}
```

Usage:
```java
@Test
public void test() {
    Map<String, String> responseBody = requestApi.sendGetRequest(endpoint);
    
    assertions.validateStatusCode("SUCCESSFUL", responseBody.get("statusCode"));
}
```

---------------------------

### Properties

#### Test Data

The `TestData` class' purpose is to provide you with a wide array of test data that can be useful in your test automation. It uses the [Java Faker](https://github.com/DiUS/java-faker) library. Similar to the other classes, you just need to instantiate this class in order to access its methods.

```java
import com.deloitte.qa.commons.properties.TestData;

public class TestAutomationCode {
    private final TestData testData = new TestData();
}
```

In this current version, these are the methods under the `TestData` class available to use:

- `getRandomFirstName()` - returns a `String` value of a random first name.
- `getRandomLastName()` - returns a `String` value of a random last name.
- `getRandomFullName()` - returns a `String` value of a random full name.
- `getRandomStreetAddress()` - returns a `String` value of a random street address.
- `getRandomCity()` - returns a `String` value of a random city.
- `getRandomAlphabeticString(int length)` - returns a `String` value of a random string following this regex: `[a-ZA-Z]{" + length + "}` where you need to pass a `length` with an `int` datatype.
- `getRandomAlphanumericString(int length)` - returns a `String` value of a random string following this regex: `[a-ZA-Z0-9]{" + length + "}` where you need to pass a `length` with an `int` datatype.
- `getRandomNumber(int length)` - returns an `int` value of a random number following this regex: `[0-9]{" + length + "}`  where you need to pass a `length` with an `int` datatype.
- `generateSpecialCharacterString(int length)` - returns a `String` value of a random string following this regex: `[!@#$%^&*()~,./;|\\[\\]_+\\-=?><{}:]{" + length + "}` where you need to pass a `length` with an `int` datatype. The character (`) is included in this regex.

Usage:
```java
private final TestData testData = new TestData();

@Test
public void test() {
    System.out.println("First Name: " + testData.getRandomFirstName());
    System.out.println("Random Number: " + testData.getRandomNumber(5));
    System.out.println("Random Alphanumeric String: " + testData.getRandomAlphanumericString(6));
}
```

Console Output:
```shell
First Name: John
Random Number: 76281
Random Alphanumeric String: h3JVz1
```

---------------------------

### Types

In Java, you can create what's called a Reference Type where you can structure your own data type similar to `String` or `int`, in order to represent a custom object that can be used and accessed by your automation.

Some response body messages that API gives tends to be similar or alike, for example, an error response. An error response usually gives a JSON body with properties like `message`, `errorCode`, `status`, etc. This is the same with responses from Authorization API requests.

> **Note:**
>
> Having a reference type for these responses, helps reduce human error in retrieving the correct properties of a JSON response because once you've mapped the JSON string response from your API, you just need to access the reference type's properties in order to retrieve its values.

In this version this framework supports two reference types:

1. `ErrorResponse`
2. `AuthResponse`

#### ErrorResponse

The `ErrorResponse` type currently includes three `String` properties: `status`, `message` and `code`. Other properties that may be returned by your API's error response can be added here in future framework updates.

Usage:

If your API returns an error response like this:

```json
{
  "status": "NOT_FOUND",
  "message": "Data not found"
}
```

You can map the returned JSON string response to the `ErrorResponse` reference type:

```java
import com.deloitte.qa.commons.types.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

private class TestAutomation {

    ErrorResponse errorResponse;

    @Test
    public void testError() {
        errorResponse = new ObjectMapper().readValue(responseMap.get("response"), ErrorResponse.class);

        assertIfEquals("NOT_FOUND", errorResponse.getStatus());
        assertIfEquals("Data not found", errorResponse.getMessage());
    }
}
```

#### AuthResponse

The `AuthResponse` type currently includes two `String` properties and one `int` property: `status`, `token` and `expiry` respectively. Other properties that may be returned by your API's authorization response can be added here in future framework updates.

Usage:

If your API returns an auth response like this:

```json
{
  "status": "SUCCESS",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
  "expiry": 18000
}
```

You can map the returned JSON string response to the `AuthResponse` reference type:

```java
import com.deloitte.qa.commons.types.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

private class TestAutomation {

    AuthResponse authResponse;

    @Test
    public void testError() {
        authResponse = new ObjectMapper().readValue(responseMap.get("response"), AuthResponse.class);

        assertIfEquals("SUCCESS", authResponse.getStatus());
        assertIfNotNull(authResponse.getToken());
    }
}
```

> **Note:**
>
> If your API response does not exactly match the properties of the reference type, here are some guidelines:
> 1. API response does not have the properties that are already in the reference type: _You can still use the reference type, properties that you don't have values to be mapped will just return a null value._
> 2. API response has properties that are not included in the properties of the reference type: _Mapping a JSON response with properties that are not in the reference type will result into an error. Kindly contact the framework's developers for the properties to be added._