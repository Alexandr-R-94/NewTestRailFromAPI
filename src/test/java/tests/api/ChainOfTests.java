package tests.api;

import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import models.Project;
import models.ProjectTypes;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ChainOfTests extends BaseApiTest {

    private int projectID;

    @Test
    public void addProjectTest() {
        Project project = Project.builder()
                .name("PR03.Alexandr")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();


        projectID = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.POST_ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");

    }

    @Test(dependsOnMethods = "addProjectTest")
    public void updateProjectTest() {
        Project updatedProject = Project.builder()
                .name("PRO3.Alexandr_update")
                .announcement("Test update")
                .is_completed(true)
                .build();

        String json = given()
                .body(updatedProject, ObjectMapperType.GSON)
                .when()
                .post(String.format(ProjectEndpoints.POST_UPDATE_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();

        Project newProject = new GsonBuilder().create().fromJson(json, Project.class);
        Assert.assertEquals(updatedProject.getName(), newProject.getName());
        Assert.assertEquals(updatedProject.getAnnouncement(), newProject.getAnnouncement());
        Assert.assertEquals(updatedProject.is_completed(), newProject.is_completed());
    }

    @Test(dependsOnMethods = "updateProjectTest")
    public void deleteProjectTest() {

        given()
                .when()
                .post(String.format(ProjectEndpoints.POST_DELETE_PROJECT, projectID))
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }
}

