package steps;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import utils.Constants;
import utils.Payload;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class apiSteps {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //    because we need it in the next calls
    String token;
    RequestSpecification request;
    Response response;
    String employeeID;
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given().header(Constants.Header_Content_Type_Key, Constants.Content_type_Value)
                .body(Payload.generateTokenPayload());
        Response response = request.when().post(Constants.GENERATE_TOKEN_URI);
        String tok = response.jsonPath().getString("token");
        token="Bearer "+tok;

    }
    @Given("a request is prepared to create an Employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request = given().header(Constants.Header_Content_Type_Key, Constants.Content_type_Value)
                .header(Constants.Header_Authorization_key, token)
                .body(Payload.createEmployeePayload());

    }
    @When("a post call is made to the endpoint")
    public void a_post_call_is_made_to_the_endpoint() {
        response = request.when().post(Constants.CREATE_EMPLOYEE_URI);
    }
    @Then("the status code is {int}")
    public void the_status_code_is(int statuscode) {
        response.then().assertThat().statusCode(statuscode);
    }
    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empid) {
        employeeID=response.jsonPath().getString(empid);

    }
    @Then("we verify that the value for key {string} is {string}")
    public void we_verify_that_the_value_for_key_is(String key, String expectedValue) {
        String actualValue = response.jsonPath().getString(key);
        Assert.assertEquals(actualValue,expectedValue);

    }

//    ---------------------------------------------------------------


    @Given("a request is prepared to update an employee in HRMS system")
    public void a_request_is_prepared_to_update_an_employee_in_hrms_system() {
        request=given().header(Constants.Header_Content_Type_Key, Constants.Content_type_Value)
                .header(Constants.Header_Authorization_key, token)
                .body(Payload.updateEmployeePayload());
    }
    @When("a PUT call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
        response=request.when().put(Constants.UPDATE_EMPLOYEE_URI);

    }
    @Then("the status code for updating the employee is {int}")
    public void the_status_code_for_updating_the_employee_is(int statuscode) {
        response.then().assertThat().statusCode(statuscode);

    }
//-------------------------------------

    @Given("a request is prepared to partially update an employee in HRMS system")
    public void a_request_is_prepared_to_partially_update_an_employee_in_hrms_system() {
        request=given().header(Constants.Header_Content_Type_Key, Constants.Content_type_Value)
                .header(Constants.Header_Authorization_key, token)
                .body(Payload.updatePartialEmployeePayload());
    }
    @When("a PATCH call is made to partially update the employee")
    public void a_patch_call_is_made_to_partially_update_the_employee() {
        response=request.when().patch(Constants.PARTIALLY_UPDATE_EMPLOYEE_URI);
    }
    @Then("the response body contains {string} key and value {string}")
    public void the_response_body_contains_key_and_value(String key, String expectedMsg) {
        String message = response.jsonPath().getString(key);
        Assert.assertEquals(message,expectedMsg);
    }



    @Given("a request call is made to get all employees in HRMS")
    public void a_request_call_is_made_to_get_all_employees_in_hrms() {
        request = given().
                header(Constants.Header_Content_Type_Key, Constants.Content_type_Value)
                .header(Constants.Header_Authorization_key, token);

    }
    @When("a Get call requested to get all employees")
    public void a_get_call_requested_to_get_all_employees() {
        response=request.when().get(Constants.GET_ALL_EMPLOYEES_URI);


    }
    @Then("response body contains of all employees")
    public void response_body_contains_of_all_employees() {
        String expectedString="employees";
        String actualString = response.body().jsonPath().prettyPrint();
        assert  actualString.contains(expectedString);

//=====================================================================
    }

    @Given("a requested call is made to get all job titles in HRMS")
    public void a_requested_call_is_made_to_get_all_job_titles_in_HRMS() {
         request = given().
        header(Constants.Header_Content_Type_Key, Constants.Content_type_Value)
                .header(Constants.Header_Authorization_key, token);
    }
    @When("a Get call is request to get all job titles")
    public void aGetCallIsRequestToGetAllJobTitles() {
        response = request.when().get(Constants.GET_ALL_JOB_TITLES_URI);
    }
  /*  @Then("status code of this request is 200")
    public void status_code_of_this_request_is_200(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }*/


    @And("response body contains of all job titles")
    public void response_body_contains_of_all_job_titles() {
        String expectedString="Jobs";
        String actualString = response.body().jsonPath().prettyPrint();
        assert  actualString.contains(expectedString);


    }

}