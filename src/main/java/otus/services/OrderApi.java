package otus.services;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.http.ContentType;
import otus.annotations.UrlPrefix;
import otus.dto.order.OrderDTO;

@UrlPrefix("/store/order")
public class OrderApi extends ServiceApiAbs {
  
  public OrderApi() {
    spec = given()
        .baseUri(getBaseUrl())
        .contentType(ContentType.JSON);
  }
  
  public void addNewOrder(OrderDTO orderDTO) {
    
    given(spec)
        .basePath(getUrlPrefix())
        .body(orderDTO)
        .log().all()
        .when()
        .post()
        .then()
        .log().all();
  }
  
  public <T> T findOrderByID(long id, int statusCode, String pathJsonSchema, Class<T> responseClass) {
    
    return given(spec)
        .basePath(getUrlPrefix() + "/" + id)
        .log().all()
        .when()
        .get()
        .then()
        .log().all()
        .statusCode(statusCode)
        .body(matchesJsonSchemaInClasspath(pathJsonSchema))
        .extract()
        .body()
        .as(responseClass);
  }
  
  public void findOrderByID(long id, int statusCode) {
    given(spec)
        .basePath(getUrlPrefix() + "/" + id)
        .log().all()
        .when()
        .get()
        .then()
        .log().all()
        .statusCode(statusCode);
  }
  
  public void deleteOrderById(long id) {
    String addPath = "/";
    
    given(spec)
        .basePath(getUrlPrefix() + addPath + id)
        .log().all()
        .when()
        .delete()
        .then()
        .log().all();
  }
  
}
