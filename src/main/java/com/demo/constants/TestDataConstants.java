package com.demo.constants;

public class TestDataConstants {
    private TestDataConstants() {
    }

    public static final String LOGIN_DATA_PATH = "src/test/resources/data/login_data.csv";
    public static final String REGISTRATION_DATA_PATH = "src/test/resources/data/registration_data.csv";
    
    // Test Case IDs
    public static final String TC_LOGIN_VALID = "TC002";
    public static final String TC_LOGIN_INVALID = "TC003";
    public static final String TC_REGISTER_USER = "TC001";
    public static final String TC_REGISTER_EXISTING_USER = "TC005";
}
