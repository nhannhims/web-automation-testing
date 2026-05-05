package com.demo.tests;

import com.demo.data.IUserData;
import com.demo.models.UserModel;
import com.demo.api.UserAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPITest implements IUserData {

    @Test(description = "API E2E: Create User Account then Delete it using Model & Interface")
    public void testCreateAndDeleteUserAccountViaAPI() {
        // --- PHẦN 1: LẤY DỮ LIỆU TỪ INTERFACE ---
        UserModel user = getValidUser();

        // --- PHẦN 2: TẠO USER MỚI ---
        Response createResponse = UserAPI.createAccount(user);

        // Verify tạo User thành công
        Assert.assertEquals(createResponse.getStatusCode(), 200, "Expected HTTP 200");
        Assert.assertEquals(createResponse.jsonPath().getString("message"), "User created!", "Create message mismatch!");
        Assert.assertEquals(createResponse.jsonPath().getInt("responseCode"), 201, "Create response code mismatch!");

        // --- PHẦN 3: XÓA USER VỪA TẠO ---
        Response deleteResponse = UserAPI.deleteAccount(user.getEmail(), user.getPassword());

        // Verify xóa User thành công
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Expected HTTP 200 for Delete");
        Assert.assertEquals(deleteResponse.jsonPath().getString("message"), "Account deleted!", "Delete message mismatch!");
        Assert.assertEquals(deleteResponse.jsonPath().getInt("responseCode"), 200, "Delete response code mismatch!");
    }
}
