import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import otus.dto.order.OrderDTO;
import otus.dto.store.StoreDTO;
import otus.dto.store.StoreOrderNotFoundResponseDTO;
import otus.dto.store.StoreResponseDTO;
import otus.services.StoreApi;

public class StoreTests {
  
  /* Поиск заказа по идентификатору.
   Проверки:
   - Статус код ответа 200.
   - Валидация по json-схеме.
   - Верификация правильности данных, полученных в ответе
  */
  @Test
  public void findOrderByIdTest() {
    StoreApi storeApi = new StoreApi();
    OrderDTO orderDTO = OrderDTO.builder()
        .id(333)
        .petId(23)
        .quantity(1)
        .shipDate("2024-10-01T04:07:47.000+0000")
        .status("placed")
        .complete(true)
        .build();
    
    StoreDTO storeDTO = StoreDTO.builder()
        .id(orderDTO.getId())
        .build();
    
    storeApi.findOrderByID(storeDTO.getId())
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schema/order/findOrderById.json"));
    
    StoreResponseDTO actualOrder = storeApi.findOrderByID(storeDTO.getId()).extract().body().as(StoreResponseDTO.class);
    
    Assertions.assertAll("order",
        () -> assertEquals(333, actualOrder.getId()),
        () -> assertEquals(23, actualOrder.getPetId()),
        () -> assertEquals(1, actualOrder.getQuantity()),
        () -> assertEquals("2024-10-01T04:07:47.000+0000", actualOrder.getShipDate()),
        () -> assertEquals("placed", actualOrder.getStatus()),
        () -> assertEquals(true, actualOrder.getComplete())
    );
  }
  
  /* Поиск заказа по неверному идентификатору.
 Проверки:
 - Статус код ответа 404.
 - Валидация по json-схеме.
 - Верификация правильности данных, полученных в ответе
*/
  @Test
  public void findOrderByIdNotFoundTest() {
    StoreApi storeApi = new StoreApi();
    
    StoreDTO storeDTO = StoreDTO.builder()
        .id(-15)
        .build();
    
    storeApi.findOrderByID(storeDTO.getId())
        .statusCode(404)
        .body(matchesJsonSchemaInClasspath("schema/order/findOrderByInvalidId.json"));
    
    StoreOrderNotFoundResponseDTO actualOrder = storeApi.findOrderByID(storeDTO.getId()).extract().body().as(StoreOrderNotFoundResponseDTO.class);
    
    Assertions.assertAll("pet",
        () -> assertEquals(1, actualOrder.getCode()),
        () -> assertEquals("error", actualOrder.getType()),
        () -> assertEquals("Order not found", actualOrder.getMessage())
    );
  }
}
