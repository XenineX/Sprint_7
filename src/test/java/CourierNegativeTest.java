import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierNegativeTest {
    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);

    CourierApi courierApi = new CourierApi();
    Courier courier = new Courier(randomLogin, randomPassword, randomName);


    @Test
    @DisplayName("для создания курьера необходимо заполнить поле Login")
    @Description("без заполненного поля Login возвращается 400 status code")
    public void checkCreateCourierWithoutLogin() {
        courier = new Courier("", randomPassword,randomName);
        courierApi.createCourier(courier)
                .then().assertThat().statusCode(400);

    }
    @Test
    @DisplayName("для создания курьера необходимо заполнить поле Login")
    @Description("без заполненного поля Login возвращается сообщение об ошибке")
    public void checkCreateCourierWithoutLoginResponseBody() {
        courier = new Courier("",randomPassword, randomName);
        courierApi.createCourier(courier)
                .then().assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }


    @Test
    @DisplayName("для создания курьера необходимо заполнить поле Password")
    @Description("без заполненного поля Password возвращается 400 status code")
    public void checkCreateCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierApi.createCourier(courier)
                .then().assertThat().statusCode(400);

    }



    @Test
    @DisplayName("для создания курьера необходимо заполнить поле Password")
    @Description("без заполненного поля Password возвращается error message")
    public void checkCreateCourierWithoutPasswordResponseBody() {
        courier = new Courier(randomLogin,"", randomName);
        courierApi.createCourier(courier)
                .then().assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }


    @Test
    @DisplayName("при создании двух одинкаковых курьеров")
    @Description("при создании двух одинкаковых курьеров ожидается 409 status code")
    public void checkCreateCourierWithSameCredentials(){
        courierApi.createCourier(courier);
        courierApi.createCourier(courier)
                .then().assertThat().statusCode(409);
    }


    @Test
    @DisplayName("при создании двух одинкаковых курьеров")
    @Description("при создании двух одинкаковых курьеров ожидается сообщение об ошибке")
    public void checkCreateCourierWithSameCredentialsResponseBody(){
        courierApi.createCourier(courier);
        courierApi.createCourier(courier)
                .then().assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }


    @Test
    @DisplayName("для авторизации нужно передать все обязательные поля")
    @Description("если поле Password не заполнено, запрос возвращает ошибку")
    public void checkLoginWithoutPasswordReturnFalse() {
        courier = new Courier(randomLogin, "", "");
        courierApi.createCourier(courier);
        courierApi.loginCourier(courier)
                .then().assertThat().statusCode(400)
                .and().body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("для авторизации нужно передать все обязательные поля")
    @Description("если поле Login незаполнено, запрос возвращает ошибку")
    public void checkLoginWithoutLoginReturnFalse() {
        courier = new Courier("", randomPassword, "");
        courierApi.loginCourier(courier)
                .then().assertThat().statusCode(400)
                .and().body("message", equalTo("Недостаточно данных для входа"));

    }


    @Test
    @DisplayName("система вернёт ошибку, если неправильно указать логин или пароль")
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void checkLoginNotExistReturnFalse() {
        courier = new Courier("абвг", "", "");

        courierApi.loginCourier(courier)
                .then().assertThat().statusCode(400)
                .and().body("message", equalTo("Недостаточно данных для входа"));

    }

    @After
    public void deleteCourier() {
        courierApi.deleteCourier(courier);
    }
}
