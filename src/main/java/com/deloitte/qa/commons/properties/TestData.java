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

    public String getRandomAlphabeticString(int length) {
        String alphabeticString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        return randomStringGenerator(alphabeticString, length);
    }

    public String getRandomAlphanumericString(int length) {
        String alphanumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        return randomStringGenerator(alphanumericString, length);
    }

    private String randomStringGenerator(String container, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(container.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(container
                    .charAt(index));
        }

        return sb.toString();
    }
}
