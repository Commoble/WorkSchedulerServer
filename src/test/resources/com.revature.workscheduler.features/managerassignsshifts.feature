Feature: Manager assigns shifts
  Scenario: Manger has ability to assign shifts
    Given The manager is already logged in to his account and on his dashboard.
    When The manager clicks  create new schedule.
    Then The manger see available employees to add to schedule.