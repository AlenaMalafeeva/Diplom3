package ru.yandex.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    public static final String MAIN_PAGE_URL = BASE_URL;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //раздел "булки"
    private By bunsTab =
            By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']/parent::div");
    //раздел "соусы"
    private By saucesTab =
            By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']/parent::div");
    //раздел "начинки"
    private By fillingsTab =
            By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']/parent::div");

    //изменение класса при выборе раздела
    private final String classTabSelected = "tab_tab_type_current";

    //кнопка "Войти в аккаунт"
    private By logInButton = By.className("button_button__33qZ0");
    //кнопка "Оформить заказ"
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //заголовок "Соберите бургег"
    private By mainPageHeader = By.xpath(".//h1[text()='Соберите бургер']");
    //анимация загрузки страницы
    private By loadingAnimation = By.className("Modal_modal__loading__3534A");


    @Step("Открываем главную страницу")
    public MainPage open() {
        driver.get(MAIN_PAGE_URL);
        waitMainPageLoad();
        return this;
    }

    @Step("Ожидание загрузки страницы MainPage")
    public MainPage waitMainPageLoad() {
        waitInvisibility(loadingAnimation);
        return this;
    }
    @Step("Клик по вкладке Булки")
    public MainPage clickBunsTab() {
        waitAndClick(bunsTab);
        return this;
    }
    @Step("Клик по вкладке Соусы")
    public MainPage clickSaucesTab() {
        waitAndClick(saucesTab);
        return this;
    }
    @Step("Клик по вкладке Начинки")
    public MainPage clickFillingsTab() {
        waitAndClick(fillingsTab);
        return this;
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public LoginPage clickLoginInButton() {
        driver.findElement(logInButton).click();
        return new LoginPage(driver);
    }

    @Step("Проверка: активна ли вкладка Булки")
    public boolean isBunsTabSelected() {
        return driver.findElement(bunsTab).getAttribute("class").contains(classTabSelected);
    }

    @Step("Проверка: активна ли вкладка Соусы")
    public boolean isSaucesTabSelected() {
        return driver.findElement(saucesTab).getAttribute("class").contains(classTabSelected);
    }

    @Step("Проверка: активна ли вкладка Начинки")
    public boolean isFillingsTabSelected() {
        return driver.findElement(fillingsTab).getAttribute("class").contains(classTabSelected);
    }
}
