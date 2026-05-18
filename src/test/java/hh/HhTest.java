package hh;

import core.BaseTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HhTest extends BaseTest {
    private final String BASE_URL = "https://hh.ru/resume/13d83cf30007dd07440039ed1f59484148765a?searchRid=1778917574336da4a62ac58f606fc006&hhtmFrom=resume_search_result";

    @Test
    public void checkAttributeHashMap() {
        HhResumePage hhResumePage = new HhResumePage(BASE_URL);
        Map<String, Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(HhResumePage.GENDER, "М");
        expectedAttributes.put(HhResumePage.CITY, "Уфа");
        expectedAttributes.put(HhResumePage.DESKTOP_ICON, true);
        expectedAttributes.put(HhResumePage.READY_TO_RELOCATE, false);

        Map<String, Object> actualAttributes = hhResumePage.getAttributes();

        Assertions.assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void checkAttributeClass() {
        HhResumePage hhResumePage = new HhResumePage(BASE_URL);
        Resume expectedAttributes = new Resume("М", "Уфа", true, false);
        Resume actualAttributes = new Resume(
                hhResumePage.getGender(),
                hhResumePage.getCity(),
                hhResumePage.isIconVisible(),
                hhResumePage.isReadyToRelocate()
        );
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expectedAttributes, actualAttributes));

    }
}
