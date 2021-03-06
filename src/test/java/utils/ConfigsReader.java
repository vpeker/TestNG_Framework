package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {
    static String filePath = Constants.CREDENTIALS_FILEPATH;

    public static Properties prop;

//	public static Properties readProperties(String filePath) {
//		try {
//			FileInputStream fis = new FileInputStream(filePath);
//			prop = new Properties();
//			prop.load(fis);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}

    public static String getProperty(String key) {

        String filePath = Constants.CREDENTIALS_FILEPATH;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }

    public static void main(String[] args) {
        String url=ConfigsReader.getProperty("url");
        System.out.println(url);

        String browser=ConfigsReader.getProperty("browser");
        System.out.println(browser);
        System.out.println(System.getProperty("user.dir"));
        String os=System.getProperty("os.name");
        System.out.println(os);
    }
}
