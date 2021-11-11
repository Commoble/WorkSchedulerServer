Feature: Manager assigns shifts
  Scenario: Manger has ability to assign shifts
    Given The manager is already logged in to his account and on his dashboard.
    When The manager clicks to create a new schedule.
    Then The manager creates new schedule.