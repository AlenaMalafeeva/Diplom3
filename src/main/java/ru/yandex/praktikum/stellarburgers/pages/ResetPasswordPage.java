package ru.yandex.praktikum.stellarburgers.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage extends BasePage {
    public static final String RECOVER_URL = BASE_URL + "forgot-password";
    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    //Кнопка "Войти"
    private By loginButton = By.xpath(".//a[@href='/login']");
    //Анимация загрузки страницы
    private By loadingAnimation = By.className("Modal_modal__loading__3534A");

    @Step("Открыть страницу воостановления пароля и дождаться загрузки")
    public ResetPasswordPage open() {
        driver.get(RECOVER_URL);
        waitInvisibility(loadingAnimation);
        return this;
    }

    @Step("Клик по кнопке \"Войти\"")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }
}
