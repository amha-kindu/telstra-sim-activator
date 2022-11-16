Feature: sim-card activation
  Scenario: successful sim-card activation
    Given a working sim-card
    When user sends sim-card activation request
    Then sim-card is activated and stored in database
  Scenario: failed sim-card activation
    Given a broken sim-card
    When  user sends sim-card activation request
    Then sim-card is not activated
