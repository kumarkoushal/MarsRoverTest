@MarsRoverTest
Feature: This Feature contains the following three tests/Test for rover direction facing and positioning initialization/Test for moving the rover x and y position and direction/Move the rover according a particular input

Scenario: Test for rover direction facing and positioning initialization, Test for moving the rover x and y position and direction, Move the rover according a particular input
Given We initialize the facing and position at 1,2 and "N"
When We move the rover with command 'L','M','L','M','L','M','L','M','M'
Then We verify the final position as 1,3 and "N"