package otus.dto.pet;

import java.util.List;
import lombok.Data;

@Data
public class PetResponseDTO {
  
  private Category category;
  private int id;
  private String name;
  private List<String> photoUrls;
  private String status;
  private List<Tag> tags;
  
}
