package stepik;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement exit = $x("//a[@class = \"btn btn-danger w-100\"]");
    private final SelenideElement welcomeText = $x("//h2[@class = \"card-title text-center mb-4\"]");

    public ProfilePage() {
        welcomeText.shouldBe(visible);
        welcomeText.shouldHave(text("Привет, admin"));
    }

    public void exit() {
        exit.click();
    }
}
