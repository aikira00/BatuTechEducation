package edu.avo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {
    public static Map<String, String> loadCredentials(String filePath) {
        Map<String, String> credentials = new HashMap<>();
        Properties props = new Properties();

        //props.load(getClass().getResourceAsStream("/credentials.properties"));
        Map<String, String> map = new HashMap<>((Map) props);

        return credentials;
    }
}
