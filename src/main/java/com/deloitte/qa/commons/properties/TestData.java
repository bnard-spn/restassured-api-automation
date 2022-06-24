package com.deloitte.qa.commons.properties;

public class TestData extends TestProperties {
    private static final String PROPERTIES = "test";

    public TestData() {
        setProperties(PROPERTIES);
        setConfiguration();
    }

    public String getEndpoint() {
        return getTestProperty("endpoint");
    }

    public String getFirstName() {
        return getRandomTestPropertyFromList("firstName");
    }

    public String getLastName() {
        return getRandomTestPropertyFromList("lastName");
    }
}
