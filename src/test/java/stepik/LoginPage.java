package stepik;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement login = $x("//input[@id = \"username\"]");
    private final SelenideElement password = $x("//input[@name= \"password\"]");
    private final SelenideElement submit = $x("//button[@class = \"btn btn-primary w-100 loginClass\"]");

    public LoginPage(String url) {
        Selenide.open(url);
    }

    public void successLogin(String loginText, String passwordText) {
        login.setValue(loginText);
        password.setValue(passwordText);
        submit.click();
    }

    public void emptyLogin() {
        submit.click();
    }
}
