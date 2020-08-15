package ru.geekbrains;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

@UtilityClass
public class DriverInitializer {

    private Properties properties = null;

    static {
        try {
            properties = new Properties();
            properties.load(DriverInitializer.class.getClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        String browser = getProperty("browser");
        if (browser != null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                default:
            }
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public String getProperty(String key) {
        return properties == null ? null : properties.getProperty(key, "");
    }
}
