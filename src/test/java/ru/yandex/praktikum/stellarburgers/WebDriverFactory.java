package ru.yandex.praktikum.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
   public static WebDriver getWebDriver(Browser browser) {
        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public enum Browser{
        CHROME,
        FIREFOX
    }
}
