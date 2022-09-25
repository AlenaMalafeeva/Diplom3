package ru.yandex.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.pages.*;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.stellarburgers.pages.LoginPage.LOGIN_URL;

public class LogInTest extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт»")
    @Description("Проверка, что клик по кнопке «Войти в аккаунт» на главной странице ведет на страницу авторизации")
    public void checkLoginUsingButtonLogInInAccount() {
        new MainPage(driver).open()
                .clickLoginInButton()
                .waitLoginPageLoad();
        assertEquals("URL doesn't match", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка, что клик по кнопке «Личный кабинет» ведет на страницу авторизации.")
    public void checkLoginUsingPersonalAccount() {
        new MainPage(driver).open();
        new Header(driver).clickPersonalAccountButton();
        new LoginPage(driver).waitLoginPageLoad();
        assertEquals("URL doesn't match", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка, что клик по кнопке в форме регистрации ведет на страницу авторизации.")
    public void checkLoginUsingRegistrationLoginButton() {
        new RegistrationPage(driver)
                .open()
                .clickLoginButton()
                .waitLoginPageLoad();
        assertEquals("URL doesn't match", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка, что клик по кнопке в форме восстановления пароля ведет на страницу авторизации.")
    public void checkLoginUsingResetLoginButton() {
        new ResetPasswordPage(driver)
                .open()
                .clickLoginButton()
                .waitLoginPageLoad();
        assertEquals("URL doesn't match", LOGIN_URL, driver.getCurrentUrl());
    }
}
