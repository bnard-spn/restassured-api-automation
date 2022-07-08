package com.deloitte.qa.commons;

import com.deloitte.qa.commons.properties.TestProperties;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.Test;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PropertiesTest {
    private TestData testData = new TestData();
    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());

    @Test
    public void getPropertyTest(){
        assertNotNull(testData.getEndpoint());
        assertNotNull(testData.getFirstName());
    }

    @Test
    public void getFakerValueTest() {
        int length = 10;
        String alphaNumString = testData.getRandomAlphanumericString(length);
        Matcher alphaNumericMatcher = Pattern.compile("[a-zA-Z\\d]{" + length + "}").matcher(alphaNumString);

        String specialCharString = testData.generateSpecialCharacterString(10);
        Matcher specialCharMatcher = Pattern.compile("[!@#$%^&*()`~,./;|\\[\\]_+\\-=?><{}:]{" + length + "}").matcher(specialCharString);

        assertTrue(alphaNumericMatcher.find());
        assertTrue(specialCharMatcher.find());
    }

    class TestData extends TestProperties {
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

        public String getRandomAlphanumericString(int length) {
            return fakeValuesService.regexify("[a-zA-Z0-9]{" + length + "}");
        }

        public String generateSpecialCharacterString(int length) {
            return fakeValuesService.regexify("[!@#$%^&*()`~,./;|\\[\\]_+\\-=?><{}:]{" + length + "}");
        }
    }
}
