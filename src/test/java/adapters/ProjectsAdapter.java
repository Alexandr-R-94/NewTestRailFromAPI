package adapters;

import baseEntities.BaseAdapter;
import com.google.common.reflect.TypeToken;
import endpoints.ProjectEndpoints;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ProjectsAdapter extends BaseAdapter {

    public List<Project> getProjects() {

        Response response = given()
                .when()
                .get(ProjectEndpoints.GET_ALL_PROJECTS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), new TypeToken<List<Project>>(){}.getType());

    }

    public Project getProject(int projectId) {

      Response response = given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, projectId))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Project.class);
    }

    public int searchProject(String projectName) {
        for (Project expectedProject : getProjects()) {
            if (expectedProject.getName().equals(projectName))
                return expectedProject.getId();
        }
        return 0;
    }

}
