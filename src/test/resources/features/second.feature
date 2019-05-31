Feature:

  Scenario: User can register on the website
    Given there is browser opened with page "https://men-men-s-01.codersguru.pl/"
    When user click on registration button
    Then user is on registration page
    When user fills all mandatory fields with values
      | fos_user_registration_form_email                | test123@test3.com |
      | fos_user_registration_form_name                 | Damian            |
      | fos_user_registration_form_lastname             | Polak             |
      | fos_user_registration_form_plainPassword_first  | Pass333           |
      | fos_user_registration_form_plainPassword_second | Pass333           |
      | form_city                                       | Krakow            |
      | form_postal_code                                | 32-800            |
      | form_street                                     | Tarnowska         |
      | form_number                                     | 123               |
    And user is loggedIn
    Then close the page