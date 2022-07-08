package com.deloitte.qa.commons.helpers;

import static org.junit.Assert.*;
public class Assertions {

    public static void assertIfTrue(boolean condition) {
        assertTrue(condition);
    }

    public static void assertIfEquals(Object expected, Object actual) {
        assertEquals(expected, actual);
    }

    public static void assertIfNotNull(Object obj) {
        assertNotNull(obj);
    }

    public static void validateStatusCode(String status, String code) {
        switch (status) {
            case "SUCCESSFUL" -> assertEquals("200", code);
            case "CREATED" -> assertEquals("201", code);
            case "NOT_FOUND" -> assertEquals("404", code);
            case "BAD_REQUEST" -> assertEquals("400", code);
            case "UNAUTHORIZED" -> assertEquals("401", code);
            case "ACCESS_FORBIDDEN" -> assertEquals("403", code);
            case "SERVICE_UNAVAILABLE" -> assertEquals("500", code);
        }
    }
}
