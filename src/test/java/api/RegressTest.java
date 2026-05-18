package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegressTest {

    private final static String BASE_URL = "https://reqres.in/";
    private final static String API_KEY = "free_user_3Dnkxj9tlKCfWhTDNEqn17VyWQi";

    @Test
    public void checkAvatarAndTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());

        List<UserData> users = given()
                .header("x-api-key", API_KEY)
                .when()
                .get("api/users/?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x -> x.getId().toString()).toList();

        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    @Test
    public void successRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .header("x-api-key", API_KEY)
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assertions.assertNotNull(successReg.getId());
        Assertions.assertNotNull(successReg.getToken());

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    public void unSuccessRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecError400());

        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .header("x-api-key", API_KEY)
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        Assertions.assertEquals("Missing password", unSuccessReg.getError());
    }

    @Test
    public void getListResourcesTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());

        List<UserInfo> users = given()
                .header("x-api-key", API_KEY)
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserInfo.class);
        Assertions.assertNotNull(users);
        List<Integer> year = users.stream().map(UserInfo::getYear).toList();
        List<Integer> sortedYear = year.stream().sorted().toList();
        Assertions.assertEquals(sortedYear, year);
    }

    @Test
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecUnique(204));
        given()
                .header("x-api-key", API_KEY)
                .when()
                .delete("api/users/2")
                .then().log().all();
    }
}
