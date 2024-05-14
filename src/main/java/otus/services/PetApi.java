package otus.services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import otus.annotations.UrlPrefix;
import otus.dto.pet.PetDTO;

@UrlPrefix("/pet")
public class PetApi extends ServiceApiAbs {
  
  private String findByStatus = "/findByStatus";
  
  public PetApi() {
    spec = given()
        .baseUri(getBaseUrl())
        .contentType(ContentType.JSON);
  }
  
  public void addNewPet(PetDTO petDTO) {
    
    given(spec)
        .basePath(getUrlPrefix())
        .body(petDTO)
        .log().all()
        .when()
        .post()
        .then()
        .log().all();
  }
  
  public void addNewPetInvalidInputGetInsteadOfPost(PetDTO petDTO, int statusCode) {
    
    given(spec)
        .basePath(getUrlPrefix())
        .body(petDTO)
        .log().all()
        .when()
        .get()
        .then()
        .log().all()
        .statusCode(statusCode);
  }
  
  public ValidatableResponse getListPetByStatus(String status) {
    
    return given(spec)
        .basePath(getUrlPrefix() + findByStatus)
        .param("status", status)
        .log().all()
        .when()
        .get()
        .then()
        .log().all();
  }
  
  public void deletePetById(Long id) {
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
