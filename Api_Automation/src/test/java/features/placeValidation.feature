Feature: Validating place API's

@AddPlace
Scenario Outline: verify if place is being successfully added or not
   Given Add place payload "<name>","<language>","<address>"
   When when user calls "AddPlaceApi" with "post" http request
   Then the API call is successs with status code 200
   And "status" in responce body is "OK"
   And "scope" in responce body is "APP"
   And verify place_ID created maps to "<name>" using "getPlaceApi"

Examples:
   
   |name   |language  |address                |
   |kiran  |Kannada   |VTU Belagavi           |
  # |Shreyas|English  |Bagalkot         
  
  @DeletePlace
  Scenario: verify if delete place functionality is working 
  Given Add deletePlace Payload
  When when user calls "deletePlaceApi" with "post" http request
  Then the API call is successs with status code 200
  And "status" in responce body is "OK"
         