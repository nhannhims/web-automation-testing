package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTest {

    @BeforeMethod
    public void setUp() {
        System.out.println("=== Setup: Before each test ===");
    }

    @Test
    public void testSamplePass() {
        System.out.println("Running: testSamplePass");
        Assert.assertTrue(true, "This test should pass");
    }

    @Test
    public void testSampleAssert() {
        System.out.println("Running: testSampleAssert");
        String expected = "Hello TestNG";
        String actual = "Hello TestNG";
        Assert.assertEquals(actual, expected, "Strings should match");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("=== Teardown: After each test ===");
    }
}
