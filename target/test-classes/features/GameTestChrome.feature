Feature: Test COVID-19 the Game on Chrome

  @Chrome
  Scenario: Create warrior and proceed to the game
  	Given I create warrior "<Name>"
  	Then I verify the welcome message
  	
  Examples:
  	| Name  					| 
  	| HandWasher      |
  	| Sanitizer				|

  @Chrome
  Scenario: Select a correct answer and verify the page
  	Given I create warrior "HandWasher"
  	When I go to page "<Page>"
  	And I verify "<Expected>" message is displayed
  	And I click the start button
  	And I select the correct answer
  	Then I verify "That is correct!" message is displayed
  	
  Examples:
  	| Page  		 				| Expected	 									    |
  	| Are you game?  		| You are in a battlefield... 		|
  	| Bus								| You have taken the public bus.. |
  	| Restaurant  			| You are seated at a restaurant..|
  	| Office						| You have entered the office.. 	|

  @Chrome
  Scenario: Select an incorrect answer and verify the page
  	Given I create warrior "HandWasher"
  	When I go to page "<Page>"
  	And I click the start button
  	And I select the incorrect answer
  	Then I verify "That doesn't sound right!" message is displayed
  	
  Examples:
  	| Page  		 				|
  	| Are you game?  		|
  	| Bus								|
  	| Restaurant  			|
  	| Office						|

  @Chrome
  Scenario: Do not select an answer and verify the page
  	Given I create warrior "Sanitizer"
  	When I go to page "<Page>"
  	And I click the start button
  	And I wait for timer to time out
  	Then I verify "Time's Up!" message is displayed
  	
  Examples:
  	| Page  		 				|
  	| Are you game?  		|
  	| Bus								|
  	| Restaurant  			|
  	| Office						|

  @Chrome
  Scenario: Select a correct answer and check score on leaderboard
  	Given I create warrior "SocialDistance"
  	When I go to page "<Page>"
  	And I click the start button
  	And I select the correct answer
  	Then I get my score and verify the leaderboard
  	
  Examples:
  	| Page  		 				|
  	| Are you game?  		|
  	| Bus								|
  	| Restaurant  			|
  	| Office						|
  	