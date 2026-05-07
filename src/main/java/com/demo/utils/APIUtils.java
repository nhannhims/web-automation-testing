package com.demo.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.parsing.Parser;

import java.util.Map;

public class APIUtils {

    static {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    /**
     * Perform a GET request
     * @param endpoint API endpoint
     * @return Response object
     */
    public static Response get(String endpoint) {
        LogUtils.info("Sending GET request to: " + endpoint);
        Response response = RestAssured.get(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Perform a GET request with query parameters
     * @param endpoint API endpoint
     * @param params Query parameters
     * @return Response object
     */
    public static Response get(String endpoint, Map<String, ?> params) {
        LogUtils.info("Sending GET request to: " + endpoint + " with params: " + params);
        Response response = RestAssured.given().queryParams(params).get(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Perform a POST request
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public static Response post(String endpoint, Object body) {
        LogUtils.info("Sending POST request to: " + endpoint);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Perform a POST request with form parameters
     * @param endpoint API endpoint
     * @param params Form parameters
     * @return Response object
     */
    public static Response postWithFormParams(String endpoint, Map<String, ?> params) {
        LogUtils.info("Sending POST request (Form Data) to: " + endpoint);
        Response response = RestAssured.given()
                .formParams(params)
                .post(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Perform a PUT request
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public static Response put(String endpoint, Object body) {
        LogUtils.info("Sending PUT request to: " + endpoint);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .put(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Perform a DELETE request
     * @param endpoint API endpoint
     * @return Response object
     */
    public static Response delete(String endpoint) {
        LogUtils.info("Sending DELETE request to: " + endpoint);
        Response response = RestAssured.delete(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Perform a DELETE request with form parameters
     * @param endpoint API endpoint
     * @param params Form parameters
     * @return Response object
     */
    public static Response deleteWithFormParams(String endpoint, Map<String, ?> params) {
        LogUtils.info("Sending DELETE request (Form Data) to: " + endpoint);
        Response response = RestAssured.given()
                .formParams(params)
                .delete(endpoint);
        logResponse(response);
        return response;
    }

    /**
     * Get a RequestSpecification with common configuration
     * @return RequestSpecification
     */
    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .contentType(ContentType.JSON);
    }

    /**
     * Log basic response details
     * @param response Response object
     */
    private static void logResponse(Response response) {
        LogUtils.info("Response Status Code: " + response.getStatusCode());
        LogUtils.debug("Response Body: " + response.getBody().asPrettyString());
    }
}
