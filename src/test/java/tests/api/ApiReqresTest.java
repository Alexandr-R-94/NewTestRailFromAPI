package tests.api;

import baseEntities.BaseApiTest;
import core.ReadProperties;
import endpoints.ReqresEndpoints;
import models.UserReqres;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class ApiReqresTest extends BaseApiTest {

    @Test
    public void getUsersList() {
        given()
                .when()
                .get(ReqresEndpoints.GET_USERS_LIST)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleUser() {
        given()
                .when()
                .get(ReqresEndpoints.GET_SINGLE_USER)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleUserNotFound() {
        given()
                .when()
                .get(ReqresEndpoints.GET_SINGLE_USER_NOT_FOUND)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getListResource() {
        given()
                .when()
                .get(ReqresEndpoints.GET_LIST)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleResource() {
        given()
                .when()
                .get(ReqresEndpoints.GET_SINGLE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleResourceNotFound() {
        given()
                .when()
                .get(ReqresEndpoints.GET_SINGLE_NOT_FOUND)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void postCreate() {
        UserReqres userReqres = UserReqres.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"job\": \"%s\"\n" +
                        "}", userReqres.getName(), userReqres.getJob()))
                .when()
                .post(ReqresEndpoints.POST_CREATE)
                .then()
                .log().body()
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void putUpdate() {
        UserReqres userReqres = UserReqres.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"job\": \"%s\"\n" +
                        "}", userReqres.getName(), userReqres.getJob()))
                .when()
                .put(ReqresEndpoints.PUT_UPDATE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void patchUpdate() {
        UserReqres userReqres = UserReqres.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"job\": \"%s\"\n" +
                        "}", userReqres.getName(), userReqres.getJob()))
                .when()
                .patch(ReqresEndpoints.PATCH_UPDATE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void deleteDelete() {

        given()
                .when()
                .delete(ReqresEndpoints.DELETE_DELETE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void postRegister_Successful() {
        UserReqres userReqres = UserReqres.builder()
                .email(ReadProperties.getInstance().getApiReqresUsername())
                .password(ReadProperties.getInstance().getApiReqresPassword())
                .build();
        given()
                .body(String.format("{\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"password\": \"%s\"\n" +
                        "}", userReqres.getEmail(), userReqres.getPassword()))
                .when()
                .post(ReqresEndpoints.POST_REGISTER_SUCCESSFUL)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postRegister_Unsuccessful() {

        given()
                .body(String.format("{\n" +
                        "  \"email\": \"%s\"\n" + "}", "sydney@fife"))
                .when()
                .post(ReqresEndpoints.POST_REGISTER_UNSUCCESSFUL)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postLogin_Successful() {
        UserReqres userReqres = UserReqres.builder()
                .email(ReadProperties.getInstance().getApiReqresUsername())
                .password("cityslicka")
                .build();
        given()
                .body(String.format("{\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"password\": \"%s\"\n" +
                        "}", userReqres.getEmail(), userReqres.getPassword()))
                .when()
                .post(ReqresEndpoints.POST_LOGIN_SUCCESSFUL)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postLogin_Unsuccessful() {

        given()
                .body(String.format("{\n" +
                        "  \"email\": \"%s\"\n" +
                        "}", "sydney@fife"))
                .when()
                .post(ReqresEndpoints.POST_LOGIN_UNSUCCESSFUL)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void getDelayedResponse() {

        given()
                .when()
                .get(ReqresEndpoints.GET_DELAYED_RESPONSE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

}
