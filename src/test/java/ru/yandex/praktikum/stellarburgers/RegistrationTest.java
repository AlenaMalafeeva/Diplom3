package ru.yandex.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.api.client.UserClient;
import ru.yandex.praktikum.stellarburgers.api.dto.User;
import ru.yandex.praktikum.stellarburgers.api.dto.UserCredentials;
import ru.yandex.praktikum.stellarburgers.pages.RegistrationPage;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;
import static ru.yandex.praktikum.stellarburgers.pages.LoginPage.LOGIN_URL;

public class RegistrationTest extends BaseTest {
    private static User user;
    private static UserClient userClient;
    private static String token;
    private final String expectedErrorMessage = "Некорректный пароль";

    @BeforeClass
    public static void init() {
        user = User.getRandomUser();
        userClient = new UserClient();
    }

    @AfterClass
    public static void deleteUser() {
        if(token != null)
            userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Регистрация")
    @Description("Проверка успешной регистрации с проверкой авторизации.")
    public void checkRegistration() {
        new RegistrationPage(driver)
                .open()
                .setNameField(user.getName())
                .setEmailField(user.getEmail())
                .setPasswordField(user.getPassword())
                .clickSignUpButton()
                .waitLoginPageLoad();
        assertEquals("url doesn't match", LOGIN_URL, driver.getCurrentUrl());

        Response response = userClient.login(UserCredentials.from(user));
        token = response.then()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");
        assertNotNull("token can't be null", token);
    }

    @Test
    @DisplayName("Проверка регистрации при некорректном пароле")
    @Description("Проверка появления ошибки при некорректном пароле. Ввод пароля из 5 символов.")
    public void checkRegistrationWithIncorrectPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver)
                .open()
                .setNameField(user.getName())
                .setEmailField(user.getEmail())
                .setPasswordField(RandomStringUtils.randomAlphabetic(4));
        registrationPage.clickSignUpButton();

        String errorMessage = registrationPage.getRegistrationError();
        assertEquals("error message doesn't match", expectedErrorMessage, errorMessage);
    }
}
