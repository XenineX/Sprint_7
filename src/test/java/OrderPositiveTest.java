import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;

    @RunWith(Parameterized.class)
    public class OrderPositiveTest {

        private final Orders orders;

        OrderApi orderApi = new OrderApi();

        public OrderPositiveTest(Orders orders) {

            this.orders = orders;
        }

        @Parameterized.Parameters
        public static Object[][] getTestData() {
            return new Object[][]{
                    {new OrderRandom(null)},
                    {new OrderRandom(new String[]{"BLACK"})},
                    {new OrderRandom(new String[]{"GREY"})},
                    {new OrderRandom(new String[]{"BLACK", "GREY"})},
            };
        }


        @Test
        public void createOrder() {
            orderApi.createOrder(orders)
                    .then().assertThat().statusCode(201)
                    .and()
                    .body("track", notNullValue());

        }
}
