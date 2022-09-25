package ru.yandex.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.yandex.praktikum.stellarburgers.api.client.UserClient;
import ru.yandex.praktikum.stellarburgers.api.dto.User;
import ru.yandex.praktikum.stellarburgers.pages.AccountPage;
import ru.yandex.praktikum.stellarburgers.pages.Header;
import ru.yandex.praktikum.stellarburgers.pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.stellarburgers.pages.MainPage.MAIN_PAGE_URL;

public class HeaderNavigationTest extends BaseTest {
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

    @Before
    public void signIn() {
        new LoginPage(driver)
                .open()
                .setEmailField(user.getEmail())
                .setPasswordField(user.getPassword())
                .clickLogInButton()
                .waitMainPageLoad();
  }

    @Test
    @DisplayName("Клик на кнопку «Личный кабинет»")
    @Description("Проверка перехода на страницу аккаунта по клику на «Личный кабинет»")
    public void checkTransferToPersonalAccount() {
        AccountPage accountPage = new Header(driver)
                .clickPersonalAccountButton()
                .waitAccountPageLoad();

        String actualEmail = accountPage.getUserEmail();
        assertEquals("Email doesn't match", user.getEmail(), actualEmail);

        String actualName = accountPage.getUserName();
        assertEquals("Name doesn't match", user.getName(), actualName);
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    @Description("Проверка перехода из личного кабинета в конструктор")
    public void checkTransferFromAccountUsingButton() {
        new Header(driver)
                .clickPersonalAccountButton()
                .waitAccountPageLoad();
        new Header(driver).clickConstructorButton();
        assertEquals("URL doesn't match", MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    @Description("Проверка перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void checkTransferFromAccountUsingLogo() {
        new Header(driver)
                .clickPersonalAccountButton()
                .waitAccountPageLoad();
        new Header(driver).clickStellarBurgers();
        assertEquals("URL doesn't match", MAIN_PAGE_URL, driver.getCurrentUrl());
    }

}
