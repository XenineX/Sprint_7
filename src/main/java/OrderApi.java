import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

public OrderApi() {

    RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
}


    @Description("base handle order create api/v1/orders")
    public Response createOrder(Orders orders){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(orders)
                .when()
                .post("/api/v1/orders");
    }

    @Description("base handle GET /api/v1/orders")
    public Response getOrder(){
        return given()
                .get("api/v1/orders");
    }

}