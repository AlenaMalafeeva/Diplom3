package ru.yandex.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public static final String LOGIN_URL = BASE_URL + "login";
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Поле для ввода email
    private By emailField = By.xpath(".//input[@type='text']");
    //Поле для пароля
    private By passwordField = By.xpath(".//input[@type='password']");
    //Кнопка "Войти"
    private By logInButton = By.className("button_button__33qZ0");
    //Кнопка "Зарегистрироваться"
    private By registerButton = By.xpath(".//a[@href='/register']");

    //Кнопка "Восстановить пароль"
    private By resetPasswordButton = By.xpath(".//a[@href='/forgot-password']");
    //анимация загрузки страницы
    private By loadingAnimation = By.className("Modal_modal__loading__3534A");

    @Step("Открыть страницу авторизации")
    public LoginPage open() {
        driver.get(LOGIN_URL);
        waitLoginPageLoad();
        return this;
    }

    @Step("Ожидание загрузки страницы LoginPage")
    public LoginPage waitLoginPageLoad() {
        waitInvisibility(loadingAnimation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        return this;
    }

    @Step("Заполнить поле email")
    public LoginPage setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Заполнить поле password")
    public LoginPage setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке \"Войти\"")
    public MainPage clickLogInButton() {
        driver.findElement(logInButton).click();
        return new MainPage(driver);
    }

    @Step("Кликнуть на \"Зарегистрироваться\"")
    public RegistrationPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return new RegistrationPage(driver);
    }

    @Step("Кликнуть на \"Восстановить пароль\"")
    public ResetPasswordPage clickResetPasswordButton() {
        driver.findElement(resetPasswordButton).click();
        return new ResetPasswordPage(driver);
    }
}
