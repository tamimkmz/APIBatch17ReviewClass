package utils;

import io.restassured.RestAssured;

import java.net.URI;

public class Constants {
    public static final String BaseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String GENERATE_TOKEN_URI = BaseURI+"/generateToken.php";
    public static final String CREATE_EMPLOYEE_URI = BaseURI+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI = BaseURI+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI = BaseURI+"/updateEmployee.php";
  //  public static final String GET_ALL_EMPLOYEE_URI = BaseURI+"/getAllEmployees.php";
    public static final String PARTIALLY_UPDATE_EMPLOYEE_URI = BaseURI
            +"/updatePartialEmplyeesDetails.php";

    public static final String Header_Content_Type_Key = "Content-Type";
    public static final String Content_type_Value = "application/json";
    public static final String Header_Authorization_key = "Authorization";
    public static final String GET_ALL_JOB_TITLES_URI = "/jobTitle.php";
    public static final String GET_ALL_EMPLOYEES_URI = "/getAllEmployees.php";
}