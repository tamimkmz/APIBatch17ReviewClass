Feature: syntax api testing

  Background: generating a JWT
    Given a JWT is generated

  @job
  Scenario:create an employee and verify that the employee is created
    And a request is prepared to create an Employee
    When a post call is made to the endpoint
    Then the status code is 201
    And the employee id "Employee.employee_id" is stored as a global variable
    And we verify that the value for key "Message" is "Employee Created"

  @job
  Scenario: Updating the employee
    Given a request is prepared to update an employee in HRMS system
    When a PUT call is made to update the employee
    Then the status code for updating the employee is 200
  @job
  Scenario: Partially Updating the employee
    Given a request is prepared to partially update an employee in HRMS system
    When a PATCH call is made to partially update the employee
    Then the status code for updating the employee is 201
    And the response body contains "Message" key and value "Employee record updated successfully"



  @job
  Scenario: getting all employees
    Given a request call is made to get all employees in HRMS
    When a Get call requested to get all employees
    Then response body contains of all employees

  @job
  Scenario: getting all job titles
    Given a requested call is made to get all job titles in HRMS
    When a Get call is request to get all job titles
    #Then status code of this request is 200
    And response body contains of all job titles