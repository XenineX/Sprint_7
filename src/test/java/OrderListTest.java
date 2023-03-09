import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {

    OrderApi orderApi = new OrderApi();

    @Test
    @DisplayName("в тело ответа возвращается список заказов")
    @Description("в тело ответа возвращается список заказов")
    public void getOrderReturnList() {

        orderApi.getOrder()
                .then()
                .body("orders", notNullValue());
    }
}
