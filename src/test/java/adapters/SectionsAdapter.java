package adapters;

import baseEntities.BaseAdapter;
import com.google.gson.reflect.TypeToken;
import endpoints.SectionsEndpoints;
import io.restassured.response.Response;
import models.Project;
import models.Sections;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SectionsAdapter extends BaseAdapter {


    public List<Sections> getSections(int projectID) {

        Response response = given()
                .when()
                .get(String.format(SectionsEndpoints.GET_ALL_SECTIONS, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), new TypeToken<List<Sections>>() {
        }.getType());

    }

    public int searchSection(String name, int projectID) {
        for (Sections expectedSection : getSections(projectID)) {
            if (expectedSection.getName().equals(name))
                return expectedSection.getId();
        }
        return 0;
    }
}
