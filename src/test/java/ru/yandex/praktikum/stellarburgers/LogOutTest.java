package ru.yandex.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.api.client.UserClient;
import ru.yandex.praktikum.stellarburgers.api.dto.User;
import ru.yandex.praktikum.stellarburgers.pages.Header;
import ru.yandex.praktikum.stellarburgers.pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.stellarburgers.pages.LoginPage.LOGIN_URL;

public class LogOutTest extends BaseTest{
    private static User user;
    private static UserClient userClient;
    private static String token;

    @BeforeClass
    public static void init() {
        user = User.getRandomUser();
        userClient = new UserClient();
        token = userClient
                .createUser(user)
                .then()
                .extract()
                .path("accessToken");
    }

    @AfterClass
    public static void deleteUser() {
        if(token != null)
            userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Клик на кнопку «Выход» в личном кабинете")
    @Description("Проверка выхода из системы по кнопке «Выход» в личном кабинете.")
    public void checkLogOut() {
        new LoginPage(driver)
                .open()
                .setEmailField(user.getEmail())
                .setPasswordField(user.getPassword())
                .clickLogInButton()
                .waitMainPageLoad();
        new Header(driver)
                .clickPersonalAccountButton()
                .waitAccountPageLoad()
                .clickExitAccountButton()
                .waitLoginPageLoad();

        assertEquals("url does not match", LOGIN_URL, driver.getCurrentUrl());
    }
}
