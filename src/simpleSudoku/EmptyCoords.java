package simpleSudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents empty coordinates of the sudoku grid.
 * 
 * @author Zotto
 */
public class EmptyCoords {
	
	public final int x; 
	public final int y; 
	public final List<Integer> values;
		
	/**
	 * Constructor;
	 * 
	 * @param x: int, collumn coordinate.
	 * @param y: int, row coordinate.
	 * @param values: List<Integer>, the possible values at a given coordinate.
	 */
	public EmptyCoords(int x, int y, List<Integer> values) { 
		this.x = x; 
		this.y = y; 
		this.values = values;
	}		 
		
	/* TODO */
	
	/**
	 * Returns a list of EmptyCoords relevant to this.
	 * An EmptyCoords is considered relevant if it share the same row, column or square with this.
	 * 
	 * @param allCoords: EmptyCoords[], all the EmptyCoords of a sudoku grid. /!\ Includes this /!\
	 * @return
	 */
	public ArrayList<EmptyCoords> getRelevantCoordinates(EmptyCoords[] allCoords){
		ArrayList<EmptyCoords> relevantCoordinates =  new ArrayList<EmptyCoords>();;

		// identify the subgrid of this:
		int squareRow = this.x/3; 
		int squareCollumn = this.y/3;
		
		// for each 
		for (EmptyCoords el : allCoords) {
						
			// check relevancy:
			if (el.equals(this)) { // same coordinate
				// pass
			}
			else if (el.x == this.x) { // same row
				
				relevantCoordinates.add(el);
				
			}
			else if (el.y == this.y) { // same column
				
				relevantCoordinates.add(el);
				
			}
			else if (el.x >= squareRow*3 && el.x < squareRow*3+3 && el.y >= squareCollumn*3 && el.y < squareCollumn*3+3) { // same square
				
				relevantCoordinates.add(el);
				
			}
		
		}
		
		return relevantCoordinates;
	}
	
	
	/* Overwritten functionalities */
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ") -> "+values.toString();
	}
		
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof EmptyCoords)){
			return false;
		}
		// type conversion:
		EmptyCoords candidate = (EmptyCoords) other;
		return Objects.equals(this.x, candidate.x) && Objects.equals(this.y, candidate.y) && Objects.equals(this.values, candidate.values);
	}
		
	
}
