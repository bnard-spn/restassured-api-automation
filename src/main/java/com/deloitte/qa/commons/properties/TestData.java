package com.deloitte.qa.commons.properties;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class TestData extends TestProperties {
    private static final String PROPERTIES = "test";
    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
    private Faker faker = new Faker();

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

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomFullName() {
        return faker.name().fullName();
    }

    public String getRandomStreetAddress() {
        return faker.address().streetAddress();
    }

    public String getRandomCity() {
        return faker.address().city();
    }

    public String getRandomAlphabeticString(int length) {
        return fakeValuesService.regexify("[a-ZA-Z]{" + length + "}");
    }

    public String getRandomAlphanumericString(int length) {
        return fakeValuesService.regexify("[a-ZA-Z0-9]{" + length + "}");
    }

    public int getRandomNumber(int length) {
        return Integer.parseInt(fakeValuesService.regexify("[0-9]{" + length + "}"));
    }

    public String generateSpecialCharacterString(int length) {
        return fakeValuesService.regexify("[!@#$%^&*()`~,./;|\\[\\]_+\\-=?><{}:]{" + length + "}");
    }
}