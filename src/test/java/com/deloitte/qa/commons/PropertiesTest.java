package com.deloitte.qa.commons;

import com.deloitte.qa.commons.properties.TestProperties;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PropertiesTest {
    private TestData testData = new TestData();

    @Test
    public void test(){
        assertNotNull(testData.getEndpoint());
        assertNotNull(testData.getFirstName());
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
    }
}
