package com.deloitte.qa.commons.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class TestProperties {
    private String properties;
    private Configuration configuration;

    public void setConfiguration() {
        Configurations configurations = new Configurations();
        String location = "src/test/resources/properties/";
        try {
            configuration = configurations.properties(new File(location + properties + ".properties"));
        } catch (Exception e) {
            System.out.println("Encountered an exception while processing one of the property configurations.");
            System.out.println(e.getMessage());
        }
    }

    public String getTestProperty(String key) {
        return configuration.getString(key);
    }

    public List<Object> getTestPropertyList(String key) {
        return configuration.getList(key);
    }

    public String getRandomTestPropertyFromList(String key) {
        List<Object> list = configuration.getList(key);
        int index = new Random().nextInt(list.size());
        return list.get(index).toString();
    }
}
