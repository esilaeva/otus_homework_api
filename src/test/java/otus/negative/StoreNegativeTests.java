package otus.negative;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import otus.dto.store.StoreOrderNotFoundResponseDTO;
import otus.extensions.ApiExtension;
import otus.services.OrderApi;

@ExtendWith(ApiExtension.class)
public class StoreNegativeTests {
  
  @Inject
  private OrderApi orderApi;
  
  /* Поиск заказа по неверному идентификатору.
   Проверки:
   - Статус код ответа 404.
   - Валидация по json-схеме.
   - Верификация правильности данных, полученных в ответе
  */
  @Test
  public void findOrderByIdNotFoundTest() {
    long id = -15L;
    
    StoreOrderNotFoundResponseDTO actualOrder = orderApi.findOrderByID(
        id,
        404,
        "schema/order/findOrderByInvalidId.json",
        StoreOrderNotFoundResponseDTO.class
    );
    
    Assertions.assertAll("pet",
        () -> assertEquals(1, actualOrder.getCode()),
        () -> assertEquals("error", actualOrder.getType()),
        () -> assertEquals("Order not found", actualOrder.getMessage())
    );
  }
  
}
