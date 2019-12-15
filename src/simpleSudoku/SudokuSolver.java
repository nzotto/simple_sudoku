package simpleSudoku;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SudokuSolver {

	SudokuGrid grid;
	static int SIZE = 9;
	static Integer[] legalValues = new Integer[] {1,2,3,4,5,6,7,8,9};
	public Map<Coords,List> pencilMap;
	
	/**
	 * Constructor
	 * @param grid, the sudoku grid we wish to solve.
	 */
	public SudokuSolver(SudokuGrid grid) {
		this.grid = grid;
		this.pencilMap = new HashMap<Coords, List>();
	}
	
	/**
	 * Creates the pencil annotation of the grid.
	 * ie. For each empty space on the grid, determine all possible numbers that could fit.
	 */
	public void createPencilMap() {
		// parse the grid:
		for (int collumn = 0; collumn < SIZE; collumn++) {
			for (int row = 0; row < SIZE; row++) {
				// if the cell is empty:
				if (grid.isEmptyAt(collumn, row)) {
					// get relevant information:
					int[] parentRow = grid.getRowOf(collumn, row);
					int[] parentCollumn = grid.getCollumnOf(collumn, row);
					int[][] parentquare = grid.getSquareOf(collumn, row);
					// list missing values:
					List<Integer> values = Arrays.asList(legalValues);
					for (int index=0; index<SIZE; index++) {
						if( values.contains((Integer) parentRow[index])) {
							values.remove((Integer) parentRow[index]);
						}
						else if( values.contains((Integer) parentCollumn[index])) {
							values.remove((Integer) parentCollumn[index]);
						}
					}
					for (int x=0; x<SIZE/3; x++) {
						for (int y=0; y<SIZE/3; y++) {
							if( values.contains((Integer) parentquare[x][y])) {
								values.remove((Integer) parentquare[x][y]);
							}
						}
					}
					// add to map:
					pencilMap.put(new Coords<Integer,Integer>(collumn, row), values);
				}
			}
		}
	}
	
	/**
	 * Private subclass used for Hashing coordinates of the sudoku grid.
	 * 
	 * @author Zotto
	 *
	 * @param <X> int, collumn coordinate.
	 * @param <Y> int, row coordinate.
	 */
	private class Coords<X, Y> { 
		public final X x; 
		public final Y y; 
		
		public Coords(X x, Y y) { 
			this.x = x; 
			this.y = y; 
		}		 
		
		@Override
	    public String toString() {
	        return "(" + x + "," + y + ")";
	    }
		
		@Override
		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			if (!(other instanceof Coords)){
				return false;
			}
			// type conversion:
			Coords<X,Y> candidate = (Coords<X,Y>) other;
			return Objects.equals(this.x, candidate.x) && Objects.equals(this.y, candidate.y);
		}
		
		@Override
	    public int hashCode() {
	        final int offset = 7;
	        int result = 1;
	        result = offset * result + x.hashCode();
	        result = offset * result + y.hashCode();
	        return result;
	    }
	}
}
