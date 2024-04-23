package otus.services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ServiceApi {
  
  public static final String BASE_URL = "https://petstore.swagger.io/v2";
  
  public RequestSpecification spec;
  
  public ServiceApi() {
    spec = given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON);
  }
}
