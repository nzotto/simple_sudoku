package simpleSudokuTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import simpleSudoku.EmptyCoords;

public class EmptyCoordsTest {
	
	@Test
	public void EmptyCoords_Constructor() {
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(5);
		EmptyCoords coord = new EmptyCoords(0, 0, values);
		
		// constructor doesn't fail
		assertTrue(true);
	}

	@Test
	public void getRelevantCoordinates_SameRow() {
		// initialize objects:
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(5);
		EmptyCoords coord1 = new EmptyCoords(0, 0, values);
		EmptyCoords coord2 = new EmptyCoords(0, 6, values);
		EmptyCoords coord3 = new EmptyCoords(8, 8, values);
		// create array of all emptyCoordinates:
		EmptyCoords[] allCoordinates = new EmptyCoords[] {coord1, coord2, coord3};
		// test:
		ArrayList<EmptyCoords> expected = new ArrayList<EmptyCoords>();
		expected.add(coord2);
		
		assertEquals(coord1.getRelevantCoordinates(allCoordinates), expected);
	}
	
	@Test
	public void getRelevantCoordinates_SameColumn() {
		// initialize objects:
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(5);
		EmptyCoords coord1 = new EmptyCoords(0, 2, values);
		EmptyCoords coord2 = new EmptyCoords(5, 2, values);
		EmptyCoords coord3 = new EmptyCoords(8, 8, values);
		// create array of all emptyCoordinates:
		EmptyCoords[] allCoordinates = new EmptyCoords[] {coord1, coord2, coord3};
		// test:
		ArrayList<EmptyCoords> expected = new ArrayList<EmptyCoords>();
		expected.add(coord2);
					
		assertEquals(coord1.getRelevantCoordinates(allCoordinates), expected);
	}
	
	@Test
	public void getRelevantCoordinates_SameSquare() {
		// initialize objects:
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(5);
		EmptyCoords coord1 = new EmptyCoords(0, 0, values);
		EmptyCoords coord2 = new EmptyCoords(2, 2, values);
		EmptyCoords coord3 = new EmptyCoords(8, 8, values);
		// create array of all emptyCoordinates:
		EmptyCoords[] allCoordinates = new EmptyCoords[] {coord1, coord2, coord3};
		// test:
		ArrayList<EmptyCoords> expected = new ArrayList<EmptyCoords>();
		expected.add(coord2);
				
		assertEquals(coord1.getRelevantCoordinates(allCoordinates), expected);
	}
	
}
