package otus.services;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;

public class StoreApi extends ServiceApi {
  
  public static final String BASE_PATH = "/store/order";
  
  public ValidatableResponse findOrderByID(int id) {
    return given(spec)
              .basePath(BASE_PATH + "/" + id)
              .log().all()
          .when()
              .get()
          .then()
              .log().all();
  }
}
