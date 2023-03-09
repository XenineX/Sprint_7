import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierPositiveTest {
    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);

    CourierApi courierApi = new CourierApi();
    Courier courier = new Courier(randomLogin, randomPassword, randomName);


    @Test
    @DisplayName("request returns correct status code")
    @Description("request returns correct status code")
    public void checkCreateCourierStatusCode() {
        courierApi.createCourier(courier)
                .then().assertThat().statusCode(201);

    }

    @Test
    @DisplayName("successful request returns ok: true")
    @Description("successful request returns ok: true")
    public void checkCreateCourierSuccessTrue() {
        courierApi.createCourier(courier)
                .then().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("successful request returns id")
    @Description("successful request returns id")
    public void checkLoginCourierSuccessReturnId() {
        courierApi.createCourier(courier);
        courierApi.loginCourier(courier)
                .then().assertThat().body("id",notNullValue());
    }

    @After
    public void deleteCourier() {
        courierApi.deleteCourier(courier);
    }
}
