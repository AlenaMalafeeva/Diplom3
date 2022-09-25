package ru.yandex.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage{
    public Header(WebDriver driver) {
        super(driver);
    }
    //Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//header//p[text()='Конструктор']");
    //Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//header//p[text()='Личный Кабинет']");
    //Логотип "Stellar Burgers"
    private By stellarBurgers = By.className("AppHeader_header__logo__2D0X2");


    @Step("Кликнуть по кнопке \"Конструктор\"")
    public MainPage clickConstructorButton() {
        waitAndClick(constructorButton);
        return new MainPage(driver);
    }

    @Step("Кликнуть по кнопке \"Личный кабинет\"")
    public AccountPage clickPersonalAccountButton() {
        waitAndClick(personalAccountButton);
        return new AccountPage(driver);
    }

    @Step("Кликнуть по логотипу \"Stellar Burgers\"")
    public MainPage clickStellarBurgers() {
        waitAndClick(stellarBurgers);
        return new MainPage(driver);
    }
}
