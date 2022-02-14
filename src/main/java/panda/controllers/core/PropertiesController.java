package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class PropertiesController {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesController.class);

    java.util.Properties properties = new java.util.Properties();
    FileInputStream fileInputStream;

    public PropertiesController(String filePath) throws IOException {
        try {
            fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    private int getIntValue(String valueName) {
        return Integer.parseInt(properties.getProperty(valueName));
    }

    private String getStrValue(String valueName) {
        return (properties.getProperty(valueName));
    }
}
