package otus.dto.store;

import lombok.Data;

@Data
public class StoreOrderNotFoundResponseDTO {
  
  private long code;
  private String message;
  private String type;
  
}
