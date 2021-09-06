package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public abstract class BaseApiTest {
    protected final Logger logger = LogManager.getLogger(this);

@BeforeTest
    public void setupTestRail() {

    RestAssured.baseURI = ReadProperties.getInstance().getURL();

    RestAssured.requestSpecification = given()
            .header(HTTP.CONTENT_TYPE, ContentType.JSON)
            .auth().preemptive().basic(
                    ReadProperties.getInstance().getUsername(),
                    ReadProperties.getInstance().getPassword());
}

//    @BeforeTest
//    public void setupReqres() {
//        RestAssured.baseURI = ReadProperties.getInstance().getApiReqresURL();
//
//        RestAssured.requestSpecification = given()
//                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
//    }
}
