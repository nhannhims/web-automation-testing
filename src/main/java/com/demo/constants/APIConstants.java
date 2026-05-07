package com.demo.constants;

public class APIConstants {
    public static final String CREATE_ACCOUNT_PATH = "/api/createAccount";
    public static final String DELETE_ACCOUNT_PATH = "/api/deleteAccount";
    public static final String GET_USER_DETAIL_BY_EMAIL_PATH = "/api/getUserDetailByEmail";

    // Response Messages
    public static final String SUCCESS_USER_CREATED = "User created!";
    public static final String SUCCESS_ACCOUNT_DELETED = "Account deleted!";
    
    // Status Codes
    public static final int SC_OK = 200;
    public static final int SC_CREATED = 201;
}
