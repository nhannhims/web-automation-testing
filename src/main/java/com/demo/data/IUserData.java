package com.demo.data;

import com.demo.models.UserModel;
import java.util.UUID;

public interface IUserData {
    
    /**
     * Get a valid user model with random email
     */
    default UserModel getValidUser() {
        String randomStr = UUID.randomUUID().toString().substring(0, 8);
        return UserModel.builder()
                .name("Nhan E2E")
                .email("e2e_" + randomStr + "@example.com")
                .password("password123")
                .title("Mr")
                .birth_date("10")
                .birth_month("10")
                .birth_year("1995")
                .firstname("Nhan")
                .lastname("E2E")
                .company("Co-Well")
                .address1("Address Line 1")
                .address2("Address Line 2")
                .country("Vietnam")
                .zipcode("12345")
                .state("Hanoi")
                .city("Hanoi")
                .mobile_number("0988777666")
                .build();
    }
}
