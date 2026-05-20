package appleinsider;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта Appleinsider.ru
 */


public class MainPage {
    //    private final SelenideElement cookie = $x("//button[@class = \"fc-button fc-cta-do-not-consent fc-secondary-button\"]");
    private final SelenideElement textBoxInput = $x("//input[@type= \"text\"]");

    public MainPage(String url) {
        Selenide.open(url);
    }

    /**
     * Выполнется поиск на сайте среди статей и нажимается кнопка Enter
     *
     * @param query - поисковая строка
     */
    public SearchPage search(String query) {
//        cookie.click();
        textBoxInput.setValue(query);
        textBoxInput.sendKeys(Keys.ENTER);
        return new SearchPage();
    }
}
