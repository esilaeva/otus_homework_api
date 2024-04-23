import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import otus.dto.pet.Category;
import otus.dto.pet.PetDTO;
import otus.dto.pet.PetResponseDTO;
import otus.services.PetApi;

public class PetTests {
  
  /* Проверка возможности добавления питомца в магазин.
Проверки:
- Статус код ответа 200.
- Валидация по json-схеме.
- Верификация правильности данных, полученных в ответе
*/
  @Test
  public void addNewPet() {
    PetApi petApi = new PetApi();
    
    PetDTO petDTO = PetDTO.builder()
        .id(1)
        .category(new Category(9, "Dog"))
        .name("doggie")
        .status("available")
        .build();
    
    petApi.addNewPet(petDTO)
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schema/addNewPet.json"))
        .body("id", equalTo(1))
        .body("category.name", equalTo("Dog"))
        .body("status", equalTo("available"));
    
    PetResponseDTO actualPet = petApi.addNewPet(petDTO).extract().body().as(PetResponseDTO.class);
    
    Assertions.assertAll("pet",
        () -> assertEquals(1, actualPet.getId()),
        () -> assertEquals(9, actualPet.getCategory().getId()),
        () -> assertEquals("Dog", actualPet.getCategory().getName()),
        () -> assertEquals("available", actualPet.getStatus())
    );
  }
  
  /* Проверка возможности добавления питомца в магазин с неверным запросом.
  Проверка:
  - Статус код ответа 405.
  */
  @Test
  public void addNewPetInvalidInput() {
    PetApi petApi = new PetApi();
    
    PetDTO petDTO = PetDTO.builder()
        .id(1)
        .category(new Category(9, "Dog"))
        .name("doggie")
        .status("available")
        .build();
    
    petApi.addNewPetInvalidInputGetInsteadOfPost(petDTO)
        .statusCode(405);
  }
}
