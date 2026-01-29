package automation.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigReader {
	
	static Properties prop;
	static {
	try{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
		prop = new Properties();
		prop.load(fis);
	}
	catch(IOException e) {
		throw new RuntimeException("Unable to load config file");
	}

}
	public static String getProperty(String key) {
		String value=prop.getProperty(key);
		return value;
	}
}
