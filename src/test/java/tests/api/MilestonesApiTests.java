package tests.api;

import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.MilestonesEndpoints;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import models.Milestone;
import models.Project;
import models.ProjectTypes;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class MilestonesApiTests extends BaseApiTest {

    private int projectID;
    private int milestoneID;

    @Test
    public void addProjectTest() {
        Project project = Project.builder()
                .name("Project for test milestones")
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
    public void addMilestoneTest() {
        Milestone milestone = Milestone.builder()
                .name("New milestone for test")
                .description("New description")
                .build();

        milestoneID = given()
                .body(milestone,ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestonesEndpoints.POST_ADD_MILESTONE, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }
    @Test(dependsOnMethods = "addMilestoneTest")
    public void updateMilestoneTest() {
        Milestone updateMilestone = Milestone.builder()
                .name("Update milestone for test")
                .description("Update description")
                .is_completed(true)
                .build();

        String json = given()
                .body(updateMilestone, ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestonesEndpoints.POST_UPDATE_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();

        Milestone newMilestone = new GsonBuilder().create().fromJson(json, Milestone.class);
        Assert.assertEquals(updateMilestone.getName(), newMilestone.getName());
        Assert.assertEquals(updateMilestone.getDescription(), newMilestone.getDescription());
        Assert.assertEquals(updateMilestone.is_completed(), newMilestone.is_completed());
    }

    @Test(dependsOnMethods = "updateMilestoneTest")
    public void getMilestoneTest() {
        given()
                .when()
                .get(String.format(MilestonesEndpoints.GET_MILESTONE, milestoneID))
                .then()
                .log().body()
                .body("name", is("Update milestone for test"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "getMilestoneTest")
    public void deleteMilestoneTest() {

        given()
                .when()
                .post(String.format(MilestonesEndpoints.POST_DELETE_MILESTONE, milestoneID))
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "deleteMilestoneTest")
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
