package com.demo.tests;

import com.demo.constants.APIConstants;
import com.demo.data.IUserData;
import com.demo.models.UserModel;
import com.demo.api.UserAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class UserAPITest implements IUserData {

    @Test(description = "API E2E: Create User Account then Delete it using Model & Interface")
    public void testCreateAndDeleteUserAccountViaAPI() {
        // --- PART 1: GET DATA FROM INTERFACE ---
        UserModel user = getValidUser();

        // --- PART 2: CREATE NEW USER ---
        Response createResponse = UserAPI.createAccount(user);

        // Verify user creation success
        createResponse.then()
                .statusCode(APIConstants.SC_OK)
                .body("message", equalTo(APIConstants.SUCCESS_USER_CREATED))
                .body("responseCode", equalTo(APIConstants.SC_CREATED));

        // --- PART 3: DELETE THE CREATED USER ---
        Response deleteResponse = UserAPI.deleteAccount(user.getEmail(), user.getPassword());

        // Verify user deletion success
        deleteResponse.then()
                .statusCode(APIConstants.SC_OK)
                .body("message", equalTo(APIConstants.SUCCESS_ACCOUNT_DELETED))
                .body("responseCode", equalTo(APIConstants.SC_OK));
    }
}
