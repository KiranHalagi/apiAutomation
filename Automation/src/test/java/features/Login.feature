Feature: Applicaton Login

Scenario: Home Page default login page

Given User is on landing page
When user login into application with username and password
Then Home page is populated
And Cards are displayed
 

