Feature: UMG Gdyni Website Tests

  Scenario: Verify homepage header text
    Given I navigate to UMG Gdyni website
    When I wait for header element to load
    Then The header text should be "Urząd Morski w Gdyni"

