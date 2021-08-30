package tests.api;

import adapters.ProjectsAdapter;
import adapters.SectionsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.CasesEndpoint;
import io.restassured.mapper.ObjectMapperType;
import models.Cases;
import models.CasesTypes;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CasesApiTests extends BaseApiTest {

    private int caseID;
    private int projectID;
    private int sectionID;
    private String projectNameWithSection = "Project for milestones and cases(section)";
    private String sectionName = "TestSection";



    @Test
    public void addCaseTest() {
        projectID = new ProjectsAdapter().searchProject(projectNameWithSection);
        sectionID = new SectionsAdapter().searchSection(sectionName, projectID);
            Cases cases = Cases.builder()
                    .title("First cases")
                    .type_id(CasesTypes.AUTOMATED)
                    .priority_id(3)
                    .build();

            caseID = given()
                    .body(cases, ObjectMapperType.GSON)
                    .when()
                    .post(String.format(CasesEndpoint.ADD_CASE, sectionID))
                    .then()
                    .log().body()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().jsonPath().get("id");
        }



        @Test(dependsOnMethods = "addCaseTest")
    public void getCase() {

                given()
                        .when()
                        .get(String.format(CasesEndpoint.GET_CASE, caseID))
                        .then()
                        .log().body()
                        .body("title", is("First cases"))
                        .body("section_id", is(2))
                        .statusCode(HttpStatus.SC_OK);
            }

    @Test(dependsOnMethods = "getCase")
    public void getHistoryOfCaseTest() {

        given()
                .when()
                .get(String.format(CasesEndpoint.GET_HISTORY_OF_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "getHistoryOfCaseTest")
    public void updateCaseTest() {
        Cases updateCase = Cases.builder()
                .title("UpdateCase")
                .priority_id(4)
                .type_id(CasesTypes.DESTRUCTIVE)
                .build();

        String json = given()
                .body(updateCase, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoint.POST_UPDATE_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();

        Cases newCases = new GsonBuilder().create().fromJson(json, Cases.class);
        Assert.assertEquals(updateCase.getTitle(), newCases.getTitle());
        Assert.assertEquals(updateCase.getPriority_id(), newCases.getPriority_id());
        Assert.assertEquals(updateCase.getType_id(), newCases.getType_id());
    }

    @Test(dependsOnMethods = "updateCaseTest")
    public void deleteCaseTest() {
        given()
                .when()
                .post(String.format(CasesEndpoint.POST_DELETE_CASE, caseID))
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}






