package ru.yandex.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    //Кнопка "Выход"
    private By exitAccountButton = By.xpath(".//button[text()='Выход']");
    //Поле Имя
    private By fieldName = By.xpath(".//label[text()='Имя']/parent::div/input");
    //Поле email
    private By fieldEmail = By.xpath(".//label[text()='Логин']/parent::div/input");

    @Step("Клик по кнопке \"Выход\"")
    public LoginPage clickExitAccountButton() {
        waitAndClick(exitAccountButton);
        return new LoginPage(driver);
    }

    @Step("Получить имя пользователя")
    public String getUserName() {
        return driver.findElement(fieldName).getAttribute("value");
    }

    @Step("Получить email пользователя")
    public String getUserEmail() {
        return driver.findElement(fieldEmail).getAttribute("value");
    }

    @Step("Ожидание загрузки страницы AccountPage")
    public AccountPage waitAccountPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitAccountButton));
        return this;
    }
}
