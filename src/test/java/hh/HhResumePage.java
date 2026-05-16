package hh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResumePage {
    public static String GENDER = "Пол";
    public static String AGE = "Возраст";
    public static String CITY = "Город";
    public static String DESKTOP_ICON = "Карта Профиля";
    public static String READY_TO_RELOCATE = "Готовность к переезду";
    private final SelenideElement gender = $x("//span[@data-qa = \"resume-personal-gender\"]");
    private final SelenideElement age = $x("//span[@data-qa=\"resume-personal-age\"]/span");
    private final SelenideElement city = $x("//span[@data-qa = \"resume-personal-address\"]");
    private final SelenideElement liveData = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement desktopIcon = $x("//div[@data-qa= \"resume-photo-desktop\"]");


    public HhResumePage(String url) {
        Selenide.open(url);
    }

    public Map<String, Object> getAttributes() {
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put(GENDER, getGender());
//        attributes.put(CITY, getCity());
//        attributes.put(DESKTOP_ICON, isIconVisible());
//        attributes.put(READY_TO_RELOCATE, isReadyToRelocate());
//        return attributes;
        return new HashMap<>() {{
            put(GENDER, getGender());
            put(CITY, getCity());
            put(DESKTOP_ICON, isIconVisible());
            put(READY_TO_RELOCATE, isReadyToRelocate());
        }};
    }

    public boolean isReadyToRelocate() {
        return !liveData.getText().split(", ")[1].equals("не готов к переезду");
    }

    public boolean isIconVisible() {
        return desktopIcon.isDisplayed();
    }

    public String getCity() {
        return city.getText();
    }

    public int getAge() {
        return Integer.parseInt(age.getText().replaceAll("\\D+", ""));
    }

    public String getGender() {
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }

    public String getGenderLow() {
        String genderValue = gender.getText();
        if (genderValue.equals("Мужчина"))
            return "М";
        else
            return "Ж";
    }
}
