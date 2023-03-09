import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierApi {

    public CourierApi() {

        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Description("base handle courier create /api/v1/courier")
    public Response createCourier(Courier courier) {
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
    }

    @Description("base handle courier login /api/v1/courier/login")
    public Response loginCourier(Courier courier) {

        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");


    }

    @Description("handle courier delete /api/v1/courier/+id")
    public void deleteCourier(Courier courier) {
        try {
            int id = loginCourier(courier).then().extract().path("id");



            given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(courier)
                    .when()
                    .delete("/api/v1/courier/"+id);

        }

        catch (NullPointerException e){
            System.out.println("Nothing to delete after test");
        }
    }
}