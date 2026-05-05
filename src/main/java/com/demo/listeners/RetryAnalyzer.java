package com.demo.listeners;

import com.demo.utils.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY_COUNT;

    static {
        String retryStr = ConfigReader.getProperty("retryCount");
        MAX_RETRY_COUNT = (retryStr != null) ? Integer.parseInt(retryStr) : 1;
    }

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < MAX_RETRY_COUNT) {
                count++;
                System.out.println(">>> Flaky detected! Retrying test '" + result.getName() + "' for the " + count + " time(s).");
                return true;
            }
        }
        return false;
    }
}
