package com.demo.utils;

import java.util.UUID;

public class RandomGenerator {

    /**
     * Generate a random string of 8 characters
     */
    public static String generateRandomString() {
        String randomStr = UUID.randomUUID().toString().substring(0, 8);
        LogUtils.debug("Generated random string: " + randomStr);
        return randomStr;
    }

    /**
     * Generate a random email address
     */
    public static String generateRandomEmail() {
        String email = "test_" + generateRandomString() + "@example.com";
        LogUtils.info("Generated random email: " + email);
        return email;
    }

    /**
     * Generate a random numeric string
     */
    public static String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        String number = sb.toString();
        LogUtils.debug("Generated random number (" + length + " digits): " + number);
        return number;
    }

    /**
     * Generate a random first name.
     */
    public static String generateRandomFirstName() {
        String name = "First" + generateRandomString();
        LogUtils.info("Generated random FirstName: " + name);
        return name;
    }

    /**
     * Generate a random last name.
     */
    public static String generateRandomLastName() {
        String name = "Last" + generateRandomString();
        LogUtils.info("Generated random LastName: " + name);
        return name;
    }

    /**
     * Generate a random street address.
     */
    public static String generateRandomAddress() {
        String address = generateRandomNumber(3) + " " + generateRandomString() + " Street";
        LogUtils.info("Generated random Address: " + address);
        return address;
    }

    /**
     * Generate a random city name.
     */
    public static String generateRandomCity() {
        String city = "City" + generateRandomString();
        LogUtils.info("Generated random City: " + city);
        return city;
    }

    /**
     * Generate a random state name.
     */
    public static String generateRandomState() {
        String state = "State" + generateRandomString();
        LogUtils.info("Generated random State: " + state);
        return state;
    }

    /**
     * Generate a random 5-digit zipcode.
     */
    public static String generateRandomZipcode() {
        return generateRandomNumber(5);
    }
}
