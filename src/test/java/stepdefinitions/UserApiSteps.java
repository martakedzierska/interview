package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class UserApiSteps {

    private int userId;
    private Response response;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String BASE_PATH_USERS = "/users/";

    @Given("a valid user ID {string}")
    public void aValidUserId(String userId) {
        this.userId = Integer.parseInt(userId);
    }

    @When("a user sends a GET request to the user API endpoint")
    public void aUserSendsAGetRequestToTheUserApiEndpoint() {
        response = RestAssured.get(BASE_URL + BASE_PATH_USERS + userId);
    }

    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the user details should match the expected values")
    public void theUserDetailsShouldMatchTheExpectedValues() {
        JsonPath jsonPath = response.jsonPath();

        assertEquals(1, jsonPath.getInt("id"));
        assertEquals("Leanne Graham", jsonPath.getString("name"));
        assertEquals("Bret", jsonPath.getString("username"));
        assertEquals("Sincere@april.biz", jsonPath.getString("email"));

        assertEquals("Kulas Light", jsonPath.getString("address.street"));
        assertEquals("Apt. 556", jsonPath.getString("address.suite"));
        assertEquals("Gwenborough", jsonPath.getString("address.city"));
        assertEquals("92998-3874", jsonPath.getString("address.zipcode"));
        assertEquals("-37.3159", jsonPath.getString("address.geo.lat"));
        assertEquals("81.1496", jsonPath.getString("address.geo.lng"));

        assertEquals("1-770-736-8031 x56442", jsonPath.getString("phone"));
        assertEquals("hildegard.org", jsonPath.getString("website"));

        assertEquals("Romaguera-Crona", jsonPath.getString("company.name"));
        assertEquals("Multi-layered client-server neural-net", jsonPath.getString("company.catchPhrase"));
        assertEquals("harness real-time e-markets", jsonPath.getString("company.bs"));
    }
}
