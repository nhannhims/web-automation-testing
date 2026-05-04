package com.demo.constants;

import java.time.Duration;

public class TimeConstants {
    // Prevent instantiation
    private TimeConstants() {
    }

    // Default wait timeouts
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration PAGE_LOAD_WAIT = Duration.ofSeconds(30);

    // Short/Long waits if needed for specific cases
    public static final Duration SHORT_WAIT = Duration.ofSeconds(5);
    public static final Duration LONG_WAIT = Duration.ofSeconds(20);
}
