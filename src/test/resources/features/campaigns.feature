Feature: Exporting promotions on the Campaigns page to excel

  Scenario: Exporting promotions of categories on the Campaigns page to excel
    Given Campaigns page opens with a web browser
    When Click on all categories on the campaigns page for promotion
    Then Promotion URLs under categories are saved in CSV format
