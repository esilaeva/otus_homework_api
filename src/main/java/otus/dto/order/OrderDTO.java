package otus.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
  
  private Boolean complete;
  private int id;
  private int petId;
  private int quantity;
  private String shipDate;
  private String status;
  
}
