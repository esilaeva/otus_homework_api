package otus.negative;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
public class PetNegativeTests implements RequiresCleanup {
  
  List<PetDTO> actualPet;
  @Inject
  private PetApi petApi;
  @Inject
  private PetDTO petDTO;
  @Inject
  private ValueAccumulator valueAccumulator;
  
  /* Проверка возможности добавления питомца в магазин с неверным запросом.
  Проверка:
  - Статус код ответа 405.
  - Животное не было добавлено при использовании некорректного запроса.
  */
  @Test
  public void addNewPetInvalidInput() {
    long id = 10000000000104L;
    Category category = new Category(9L, "Dog");
    String name = "Druzhok";
    String status = "available";
    
    petDTO = PetDTO.builder()
        .id(id)
        .category(category)
        .name(name)
        .status(status)
        .build();
    
    petApi.addNewPetInvalidInputGetInsteadOfPost(petDTO, 405);
    valueAccumulator.id = id;
    
    Response response = petApi.getListPetByStatus(status)
        .extract()
        .response();
    
    actualPet = response.jsonPath().getList(EMPTY, PetDTO.class);
    
    Optional<PetDTO> pets = actualPet.stream()
        .filter(pet -> pet.getId() == id)
        .findFirst();
    
    assertFalse(pets.isPresent(), "A pet with this ID should not be found");
  }
  
  @Override
  public void cleanup() {
    if (valueAccumulator.id != -1L) {
      petApi.deletePetById(petDTO.getId());
      valueAccumulator.id = -1L;
    }
  }
}
