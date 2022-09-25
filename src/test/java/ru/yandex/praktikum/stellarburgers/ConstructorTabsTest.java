package ru.yandex.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.pages.MainPage;
import static org.junit.Assert.assertTrue;

public class ConstructorTabsTest extends BaseTest{

    @Test
    @DisplayName("Переход к разделам")
    @Description("Проверка переходов между разделами конструктора.")
    public void checkConstructorTabs() {
        boolean isSuccess = new MainPage(driver)
                .open()
                .clickSaucesTab()
                .isSaucesTabSelected();
        assertTrue("sauces tab is not open", isSuccess);

        isSuccess = new MainPage(driver)
                .clickBunsTab()
                .isBunsTabSelected();
        assertTrue("buns tab is not open", isSuccess);

        isSuccess = new MainPage(driver)
                .clickFillingsTab()
                .isFillingsTabSelected();
        assertTrue("fillings tab is not open", isSuccess);
    }
}
