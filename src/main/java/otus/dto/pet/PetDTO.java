package otus.dto.pet;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
  
  private Category category;
  private int id;
  private String name;
  private List<String> photoUrls;
  private String status;
  private List<Tag> tags;
}
