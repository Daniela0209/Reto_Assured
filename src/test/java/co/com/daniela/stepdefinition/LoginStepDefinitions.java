package co.com.daniela.stepdefinition;

import co.com.daniela.stepdefinition.setup.services.SetIUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LoginStepDefinitions extends SetIUp {

    public static final Logger LOGGER = Logger.getLogger(LoginStepDefinitions.class);
    private Response response;
    private RequestSpecification request;

    @Given("el usuario esta en la pagina de inicio de sesion con el correo de usuario {string} y la contrasena {string}")
    public void elUsuarioEstaEnLaPaginaDeInicioDeSesionConElCorreoDeUsuarioYLaContrasena(String email, String password) {

        try {

            general_SetUp();
            request = given()
                    .contentType(ContentType.JSON)
                    .body(body(email,password));

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @When("cuando el usuario hace una peticion de inicio")
    public void cuandoElUsuarioHaceUnaPeticionDeInicio()  {


        try {
            response = request.when()
                    .post(BASE_LOGIN_RESOURCE);

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }

    }
    @Then("el usuario debera ver un codigo de respuesta exitoso y un token de respuesta")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYUnTokenDeRespuesta() {


        try {

            response.then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token",notNullValue());
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    private String body(String email, String password) {

        return "{" +
                "   \"email\":\""+email+"\","+
                "   \"password\":\""+password+"\""+
                "}";
    }

}
