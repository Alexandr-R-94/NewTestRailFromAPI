package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import models.Project;
import models.ProjectTypes;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestRailApiTest extends BaseApiTest {

    private int projectID;

    @Test
    public void getAllProjects() {

        given()
                .when()
                .get(ProjectEndpoints.GET_ALL_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllProjectsAdapterTest() {

        List<Project> projectList = new ProjectsAdapter().getProjects();

        System.out.println(projectList.get(0));

    }

    @Test
    public void getProjectDetailsTest() {
        int projectId = 96;

        given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, projectId))
                .then()
                .log().body()
                .body("name", is("Project for milestones and cases(section)"))
                .body("is_completed", equalTo(false))
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void getAdaptorProjectDetailsTest() {
        int projectId = 96;

        Project actualProject = new ProjectsAdapter().getProject(projectId);

        System.out.println(actualProject);

    }

    @Test
    public void addProjectTest() {
        Project project = Project.builder()
                .name("PR01")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", project.getName());
        jsonAsMap.put("suite_mode", project.getSuite_mode());

        given()
                .body(jsonAsMap)
                .when()
                .post(ProjectEndpoints.POST_ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void addProjectTestTwo() {
        Project project = Project.builder()
                .name("PR03")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();


        projectID =  given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.POST_ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");

    }

    @Test
    public void staticJsonValidationTest() throws FileNotFoundException {
       Project expectedProject = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
               .fromJson(new FileReader("src/test/resources/expectedProject.json"), Project.class);

       Project actualProject = new ProjectsAdapter().getProject(96);

       Assert.assertEquals(expectedProject, actualProject);
    }
    }

