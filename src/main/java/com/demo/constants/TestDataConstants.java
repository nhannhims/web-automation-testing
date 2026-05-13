package com.demo.constants;

public class TestDataConstants {
    private TestDataConstants() {
    }

    public static final String LOGIN_DATA_PATH = "src/test/resources/data/login_data.csv";
    public static final String REGISTRATION_DATA_PATH = "src/test/resources/data/registration_data.csv";
    public static final String CONTACT_US_DATA_PATH = "src/test/resources/data/contact_us_data.csv";
    public static final String PLACE_ORDER_DATA_PATH = "src/test/resources/data/place_order_data.csv";

    // Test Case IDs
    public static final String TC_LOGIN_VALID = "TC002";
    public static final String TC_LOGIN_INVALID = "TC003";
    public static final String TC_REGISTER_USER = "TC001";
    public static final String TC_REGISTER_EXISTING_USER = "TC005";
    public static final String TC_CONTACT_US = "TC006";
    public static final String TC_PLACE_ORDER = "TC015";
    public static final String TC_SEARCH_VERIFY_CART_AFTER_LOGIN = "TC020";

    public static final String UPLOAD_FILE_PATH = FrameworkConstants.PROJECT_PATH
            + "/src/test/resources/data/upload_test.txt";
    public static final String CONTACT_US_SUCCESS_MSG = "Success! Your details have been submitted successfully.";
    public static final String SEARCH_PRODUCT_NAME = "Blue Top";
    public static final String ALL_PRODUCTS_HEADING = "All Products";
    public static final String SEARCHED_PRODUCTS_HEADING = "Searched Products";
    public static final String ORDER_PLACED_SUCCESS_MSG = "Your order has been placed successfully!";
    
    // Login Data
    public static final String VALID_USER_EMAIL = "huunhan.test@test.vn";
    public static final String VALID_USER_PASSWORD = "Password@123";
    
    // Payment Data
    public static final String CARD_NAME = "John Doe";
    public static final String CARD_NUMBER = "4111111111111111";
    public static final String CARD_CVC = "123";
    public static final String CARD_EXP_MONTH = "12";
    public static final String CARD_EXP_YEAR = "2025";
    public static final String ORDER_COMMENT = "This is a test comment.";
}
