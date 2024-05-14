package otus.positive;

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.Inject;
import io.restassured.response.Response;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import otus.dto.pet.Category;
import otus.dto.pet.PetDTO;
import otus.extensions.ApiExtension;
import otus.extensions.RequiresCleanup;
import otus.services.PetApi;
import otus.utils.ValueAccumulator;

@ExtendWith(ApiExtension.class)
public class PetPositiveTests implements RequiresCleanup {
  
  List<PetDTO> actualPet;
  @Inject
  private PetApi petApi;
  @Inject
  private ValueAccumulator valueAccumulator;
  @Inject
  private PetDTO petDTO;
  
  /* Проверка возможности добавления питомца в магазин.
    Проверки:
    - Статус код ответа 200.
    - Верификация правильности данных, полученных в ответе
  */
  @Test
  public void addNewPetTest() {
    long id = 1086L;
    Category category = new Category(9L, "Dog");
    String name = "Druzhok";
    String status = "available";
    
    petDTO = PetDTO.builder()
        .id(id)
        .category(category)
        .name(name)
        .status(status)
        .build();
    
    petApi.addNewPet(petDTO);
    valueAccumulator.id = id;
    
    Response response = petApi.getListPetByStatus(status).extract().response();
    actualPet = response.jsonPath().getList("", PetDTO.class);
    
    Optional<PetDTO> pets = actualPet.stream()
        .filter(pet -> pet.getId() == id)
        .findFirst();
    
    assertAll("pet",
        () -> assertEquals(200, response.getStatusCode(), "Status codes must match"),
        () -> assertTrue(pets.isPresent(), "A pet with this id must be found"),
        () -> assertEquals(id, pets.map(PetDTO::getId).orElse(-1L), "IDs must match"),
        () -> assertEquals(category.getId(), pets.map(p -> p.getCategory().getId()).orElse(-1L), "Category IDs must match")
    );
  }
  
  @Override
  public void cleanup() {
    if (valueAccumulator.id != -1L) {
      petApi.deletePetById(petDTO.getId());
      valueAccumulator.id = -1L;
    }
    
    Optional<PetDTO> pets = actualPet.stream()
        .filter(pet -> pet.getId() == valueAccumulator.id)
        .findFirst();
    
    assertFalse(pets.isPresent(), "A pet with this id should not be found");
  }
  
}
