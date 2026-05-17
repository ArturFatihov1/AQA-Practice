package stepik;

import core.BaseTest;
import org.junit.Test;

public class StepikTest extends BaseTest {
    private static final String BASE_URL = "http://qa-stand-login.inzhenerka.tech/login";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin123";

    @Test
    public void loginWithAdminForm() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.successLogin(LOGIN, PASSWORD);
        ProfilePage profilePage = new ProfilePage();
        profilePage.exit();
    }

    @Test
    public void loginWithEmptyForm() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.emptyLogin();
        ProfilePage profilePage = new ProfilePage();
        profilePage.exit();
    }
}

