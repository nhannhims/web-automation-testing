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
        // --- PHẦN 1: LẤY DỮ LIỆU TỪ INTERFACE ---
        UserModel user = getValidUser();

        // --- PHẦN 2: TẠO USER MỚI ---
        Response createResponse = UserAPI.createAccount(user);

        // Verify tạo User thành công
        createResponse.then()
                .statusCode(APIConstants.SC_OK)
                .body("message", equalTo(APIConstants.SUCCESS_USER_CREATED))
                .body("responseCode", equalTo(APIConstants.SC_CREATED));

        // --- PHẦN 3: XÓA USER VỪA TẠO ---
        Response deleteResponse = UserAPI.deleteAccount(user.getEmail(), user.getPassword());

        // Verify xóa User thành công
        deleteResponse.then()
                .statusCode(APIConstants.SC_OK)
                .body("message", equalTo(APIConstants.SUCCESS_ACCOUNT_DELETED))
                .body("responseCode", equalTo(APIConstants.SC_OK));
    }
}
