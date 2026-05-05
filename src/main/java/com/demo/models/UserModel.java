package com.demo.models;

import lombok.Builder;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class UserModel {
    private String name;
    private String email;
    private String password;
    private String title;
    private String birth_date;
    private String birth_month;
    private String birth_year;
    private String firstname;
    private String lastname;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobile_number;

    /**
     * Convert the object to a Map for API form parameters
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);
        map.put("title", title);
        map.put("birth_date", birth_date);
        map.put("birth_month", birth_month);
        map.put("birth_year", birth_year);
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        map.put("company", company);
        map.put("address1", address1);
        map.put("address2", address2);
        map.put("country", country);
        map.put("zipcode", zipcode);
        map.put("state", state);
        map.put("city", city);
        map.put("mobile_number", mobile_number);
        return map;
    }
}
