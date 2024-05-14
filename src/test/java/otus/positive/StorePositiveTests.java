package otus.positive;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import otus.dto.order.OrderDTO;
import otus.dto.store.StoreResponseDTO;
import otus.extensions.ApiExtension;
import otus.extensions.RequiresCleanup;
import otus.services.OrderApi;
import otus.utils.ValueAccumulator;

@ExtendWith(ApiExtension.class)
public class StorePositiveTests implements RequiresCleanup {
  
  @Inject
  OrderDTO orderDTO;
  @Inject
  private OrderApi orderApi;
  @Inject
  private ValueAccumulator valueAccumulator;
  private StoreResponseDTO actualOrder;
  
  /* Поиск заказа по идентификатору.
   Проверки:
   - Статус код ответа 200.
   - Валидация по json-схеме.
   - Верификация правильности данных, полученных в ответе
  */
  @Test
  public void findOrderByIdTest() {
    long id = 125500L;
    long petId = 1589L;
    int quantity = 55;
    String shipDate = "2024-02-10T17:34:02.982+0000";
    String status = "placed";
    Boolean complete = true;
    
    orderDTO = OrderDTO.builder()
        .id(id)
        .petId(petId)
        .quantity(quantity)
        .shipDate(shipDate)
        .status(status)
        .complete(complete)
        .build();
    
    orderApi.addNewOrder(orderDTO);
    valueAccumulator.id = id;
    
    actualOrder = orderApi.findOrderByID(
        id,
        200,
        "schema/order/findOrderById.json",
        StoreResponseDTO.class
    );
    
    assertAll("order",
        () -> assertEquals(id, actualOrder.getId(), "IDs must match"),
        () -> assertEquals(petId, actualOrder.getPetId(), "Pet IDs must match"),
        () -> assertEquals(quantity, actualOrder.getQuantity(), "Quantities must match"),
        () -> assertEquals(shipDate, actualOrder.getShipDate(), "Ship dates must match"),
        () -> assertEquals(status, actualOrder.getStatus(), "Statuses must match"),
        () -> assertEquals(complete, actualOrder.getComplete(), "Order completion status should be true")
    );
  }
  
  @Override
  public void cleanup() {
    if (valueAccumulator.id != -1L) {
      orderApi.deleteOrderById(orderDTO.getId());
      
      orderApi.findOrderByID(valueAccumulator.id, 404);
      
      valueAccumulator.id = -1L;
    }
  }
  
}
