package ru.yandex.praktikum.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver(WebDriverFactory.Browser.CHROME);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
