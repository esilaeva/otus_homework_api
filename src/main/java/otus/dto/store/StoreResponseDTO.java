package otus.dto.store;

import lombok.Data;

@Data
public class StoreResponseDTO {
  
  private Boolean complete;
  private long id;
  private long petId;
  private int quantity;
  private String shipDate;
  private String status;
}
