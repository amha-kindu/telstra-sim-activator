Feature: SimCard activation
  Scenario: Successful sim-card activation
    When user sends iccid With "1255789453849037777"
    Then SimCard activation "successful"

  Scenario: Failed sim-card activation
    When user sends iccid With "8944500102198304826"
    Then SimCard activation "failed"
