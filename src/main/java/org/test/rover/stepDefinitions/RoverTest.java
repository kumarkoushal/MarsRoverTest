package org.test.rover.stepDefinitions;

import java.util.HashMap;

import org.junit.Assert;
import org.test.rover.Position;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RoverTest {

	/**
	 *   | N S E W
	 * - |---------
	 * L | W E N S
	 * R | E W S N
	 * M | N S E W
	 */
	
	static HashMap<Character, Integer> directions;
	static HashMap<Character, Integer> moves;
	static char[][] matrix;
	static Position position;
	static int[][] space;

	@Given("We initialize the facing and position at (.*),(.*) and \"([^\"]*)\"")
	public void initializePositionAndFacingOfRover(int x, int y, String direction) {
		space = new int[5][5];
		populateDirections();
		populateMoves();
		buildDirectionMatrix(directions.size(), moves.size());
		position = new Position(x, y, direction.charAt(0));
	}
	
	@When("We move the rover with command '(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)'")
	public void commandsTest(String a, String b, String c, String d, String e, String f, String g, String h, String i) {
        char[] commands = new char[]{a.charAt(0), b.charAt(0), c.charAt(0), d.charAt(0), e.charAt(0), f.charAt(0), g.charAt(0), h.charAt(0), i.charAt(0)};
        for (char command : commands) {
            executeCommand(command);
        }
        System.out.println("*************");
        System.out.println("X Coordinate: "+position.getX());
        System.out.println("Y Coordinate: "+position.getY());
        System.out.println("Direction: "+position.getDir());
    }
	
	@Then("We verify the final position as (.*),(.*) and \"([^\"]*)\"")
	public void verifyLocation(int x, int y, String direction) {
        Assert.assertEquals(x, position.getX());
        Assert.assertEquals(y, position.getY());
        Assert.assertEquals(direction.charAt(0), position.getDir());
	}

	private void buildDirectionMatrix(int col, int row) {
		matrix = new char[row][col];
		matrix[0][0] = 'W';
		matrix[0][1] = 'E';
		matrix[0][2] = 'N';
		matrix[0][3] = 'S';
		matrix[1][0] = 'E';
		matrix[2][1] = 'W';
		matrix[1][2] = 'S';
		matrix[1][3] = 'N';
		matrix[2][0] = 'N';
		matrix[2][1] = 'S';
		matrix[2][2] = 'E';
		matrix[2][3] = 'W';
	}

	/**
	 * Helper function to build map move to a row in matrix
	 */
	private void populateMoves() {
		moves = new HashMap<Character, Integer>();
		moves.put('L', 0);
		moves.put('R', 1);
		moves.put('M', 2);
	}

	/**
	 * Helper function to build map direction to a col in matrix
	 */
	private void populateDirections() {
		directions = new HashMap<Character, Integer>();
		directions.put('N', 0);
		directions.put('S', 1);
		directions.put('E', 2);
		directions.put('W', 3);
	}

    /**
     * Command to move the rovers and change direction
     */
    private void executeCommand(char command) {
        char updateDirection = getUpdateDirection(command, position.getDir());
        int[] updatePosition;
        if (command == 'M') {
            updatePosition = getUpdateLocation(position.getX(), position.getY(), updateDirection);
            position.setX(updatePosition[0]);
            position.setY(updatePosition[1]);
        }
        position.setDir(updateDirection);
    }

    private char getUpdateDirection(char movement, char oldDirection) {
        char updatedDir = matrix[moves.get(movement)][directions.get(oldDirection)];
        return updatedDir;
    }

    private int[] getUpdateLocation(int X, int Y, char direction) throws IllegalStateException {
        switch (direction) {
            case 'N':
                Y += 1;
                break;
            case 'S':
                Y -= 1;
                break;
            case 'E':
                X += 1;
                break;
            case 'W':
                X -= 1;
                break;
        }
        if (X < 0 || Y < 0 || X >= 5 || Y >= 5)
            throw new IllegalStateException("rover can not move");
        int[] loc = new int[2];
        loc[0] = X;
        loc[1] = Y;
        return loc;
    }
}
