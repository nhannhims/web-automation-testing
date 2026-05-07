package com.demo.api;

import com.demo.constants.APIConstants;
import com.demo.constants.FrameworkConstants;
import com.demo.utils.ConfigReader;
import com.demo.models.UserModel;
import com.demo.utils.APIUtils;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class UserAPI {

    private static final String BASE_URL = ConfigReader.getProperty(FrameworkConstants.URL);
    private static final String CREATE_ACCOUNT_ENDPOINT = BASE_URL + APIConstants.CREATE_ACCOUNT_PATH;
    private static final String DELETE_ACCOUNT_ENDPOINT = BASE_URL + APIConstants.DELETE_ACCOUNT_PATH;

    /**
     * Create/Register User Account via API
     * 
     * @param userData Map containing user data (name, email, password, etc.)
     * @return Response object
     */
    public static Response createAccount(Map<String, String> userData) {
        return APIUtils.postWithFormParams(CREATE_ACCOUNT_ENDPOINT, userData);
    }

    /**
     * Create/Register User Account using UserModel
     * 
     * @param user UserModel object
     * @return Response object
     */
    public static Response createAccount(UserModel user) {
        return createAccount(user.toMap());
    }

    /**
     * Delete User Account via API
     * 
     * @param email User email
     * @param password User password
     * @return Response object
     */
    public static Response deleteAccount(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return APIUtils.deleteWithFormParams(DELETE_ACCOUNT_ENDPOINT, params);
    }

    /**
     * Helper method to build user data map for createAccount
     */
    public static Map<String, String> buildUserData(String name, String email, String password, String title, 
                                                   String birth_date, String birth_month, String birth_year,
                                                   String firstname, String lastname, String company,
                                                   String address1, String address2, String country,
                                                   String zipcode, String state, String city, String mobile_number) {
        Map<String, String> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("email", email);
        userData.put("password", password);
        userData.put("title", title);
        userData.put("birth_date", birth_date);
        userData.put("birth_month", birth_month);
        userData.put("birth_year", birth_year);
        userData.put("firstname", firstname);
        userData.put("lastname", lastname);
        userData.put("company", company);
        userData.put("address1", address1);
        userData.put("address2", address2);
        userData.put("country", country);
        userData.put("zipcode", zipcode);
        userData.put("state", state);
        userData.put("city", city);
        userData.put("mobile_number", mobile_number);
        return userData;
    }
}
