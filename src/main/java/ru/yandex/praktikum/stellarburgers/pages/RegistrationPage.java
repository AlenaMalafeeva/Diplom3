package ru.yandex.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage{
    public static final String REGISTER_URL = BASE_URL + "register";
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //Кнопка "Войти"
    private By loginButton = By.xpath(".//a[@href='/login']");
    //Поле "Имя"
    private By nameField = By.className("input__textfield");
    //Поле "Email"
    private By emailField = By.className("input__textfield");
    //Поле "Пароль"
    private By passwordField = By.xpath(".//input[@type='password']");
    //Кнопка "Зарегистрироваться"
    private By signUpButton = By.className("button_button__33qZ0");
    //Ошибка некорректного пароля
    private By registrationError = By.className("input__error");
    //анимация загрузки страницы
    private By loadingAnimation = By.className("Modal_modal__loading__3534A");

    @Step("Открыть страницу регистрации и дождаться загрузки")
    public RegistrationPage open() {
        driver.get(REGISTER_URL);
        waitInvisibility(loadingAnimation);
        return this;
    }

    @Step("Клик по кнопке \"Войти\"")
    public LoginPage clickLoginButton() {
        waitAndClick(loginButton);
        return new LoginPage(driver);
    }

    @Step("Заполнить поле \"Имя\"")
    public RegistrationPage setNameField(String name) {
        driver.findElements(nameField).get(0).sendKeys(name);
        return this;
    }

    @Step("Заполнить поле \"email\"")
    public RegistrationPage setEmailField(String email) {
        driver.findElements(emailField).get(1).sendKeys(email);
        return this;
    }

    @Step("Заполнить поле \"Пароль\"")
    public RegistrationPage setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке \"Зарегистрироваться\"")
    public LoginPage clickSignUpButton() {
        driver.findElement(signUpButton).click();
        return new LoginPage(driver);
    }

    @Step("Проверить появление ошибки \"Некорректный пароль\"")
    public String getRegistrationError() {
        return driver.findElement(registrationError).getText();
    }
}
