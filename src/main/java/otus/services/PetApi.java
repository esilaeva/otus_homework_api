package otus.services;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import otus.dto.pet.PetDTO;

public class PetApi extends ServiceApi {
  
  public static final String BASE_PATH = "/pet";
  
  public ValidatableResponse addNewPet(PetDTO petDTO) {
    
    return given(spec)
        .basePath(BASE_PATH)
        .body(petDTO)
        .log().all()
        .when()
        .post()
        .then()
        .log().all();
  }
  
  public ValidatableResponse addNewPetInvalidInputGetInsteadOfPost(PetDTO petDTO) {
    
    return given(spec)
        .basePath(BASE_PATH)
        .body(petDTO)
        .log().all()
        .when()
        .get()
        .then()
        .log().all();
  }
}
