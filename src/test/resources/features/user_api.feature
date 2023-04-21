Feature: User API

  Scenario: Validate user details for a given user ID
    Given a valid user ID "1"
    When a user sends a GET request to the user API endpoint
    Then the response should have a status code of 200
    And the user details should match the expected values