package simpleSudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SudokuSolver {

	private SolvingStrategy strat;
	private SudokuGrid grid;
	static int SIZE = 9;
	static Integer[] legalValues = new Integer[] {1,2,3,4,5,6,7,8,9};
	public List<EmptyCoords> pencil;
	
	/**
	 * Constructor
	 * @param grid, the sudoku grid we wish to solve.
	 * @param strat, a solving strategy. Used to determine which solving algorythm will be used.
	 */
	public SudokuSolver(SudokuGrid grid, SolvingStrategy strat) {
		// assign elements:
		this.grid = grid;
		this.pencil = new ArrayList<EmptyCoords> ();
		this.strat = strat;
	}
	
	/**
	 * Constructor
	 * @param grid, the sudoku grid we wish to solve.
	 */
	public SudokuSolver(SudokuGrid grid) {
		// assign elements:
		this.grid = grid;
		this.pencil = new ArrayList<EmptyCoords> ();
		this.strat = SolvingStrategy.PROBA;
	}
	
	public void run() {
		// annotate all possible values at each empty coordinate:
		createPencilMarks();
		// for each empty coordinate:
		for (EmptyCoords el: pencil) {
			if (el.values.size() == 1) {
				grid.setValue(el.x, el.y, el.values.get(0));
			}
			else {
				// TODO: select algorithm
			}
		}
	}
	
	/**
	 * Creates the pencil annotation of the grid.
	 * ie. For each empty space on the grid, determine all possible numbers that could fit.
	 */
	private void createPencilMarks() {
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
					List<Integer> values = new ArrayList<Integer>(Arrays.asList(legalValues));
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
					// create object and add to container:
					pencil.add(new EmptyCoords(collumn, row, values));
				}
			}
		}
	}
	
	public SudokuGrid getGrid() {
		return this.grid;
	}
	
	/**
	 * Private subclass used for representing empty coordinates of the sudoku grid.
	 * 
	 * @author Zotto
	 *
	 * @param x: int, collumn coordinate.
	 * @param y: int, row coordinate.
	 * @param values: List<Integer>, the possible values at a given coordinate.
	 */
	private class EmptyCoords {
		public final int x; 
		public final int y; 
		public final List<Integer> values;
		
		public EmptyCoords(int x, int y, List<Integer> values) { 
			this.x = x; 
			this.y = y; 
			this.values = values;
		}		 
		
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
		
		@Override
	    public int hashCode() {
	        final int offset = 7;
	        int result = 1;
	        result = offset * result + ((Integer) x).hashCode();
	        result = offset * result + ((Integer) y).hashCode();
	        result = offset * result + values.hashCode();
	        return result;
	    }
	}
}
