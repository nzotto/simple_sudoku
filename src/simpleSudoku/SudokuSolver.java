package simpleSudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SudokuSolver {

	private SolvingStrategy strat;
	private SudokuGrid grid;
	static int SIZE = 9;
	static Integer[] legalValues = new Integer[] {1,2,3,4,5,6,7,8,9};
	private List<EmptyCoords> pencil;
	private SudokuVerifier verificator;
	
	/**
	 * Constructor.
	 * @param grid, the sudoku grid we wish to solve.
	 * @param strat, a solving strategy. Used to determine which solving algorythm will be used.
	 */
	public SudokuSolver(SudokuGrid grid, SolvingStrategy strat) {
		// assign elements:
		this.grid = grid;
		this.pencil = new ArrayList<EmptyCoords> ();
		this.strat = strat;
		this.verificator = new SudokuVerifier();
	}
	
	/**
	 * Constructor.
	 * The solving strategy defaults to the probabilistic one.
	 * @param grid, the sudoku grid we wish to solve.
	 */
	public SudokuSolver(SudokuGrid grid) {
		// assign elements:
		this.grid = grid;
		this.pencil = new ArrayList<EmptyCoords> ();
		this.verificator = new SudokuVerifier();
		this.strat = SolvingStrategy.PROBABILISTIC;
	}
	
	public void run() {
		// annotate all possible values at each empty coordinate:
		createPencilMarks();
		// fill coordinates with only one answer:
		for (EmptyCoords el: pencil) {
			
			System.out.println(el);
			
			if (el.values.size() == 1) {
				grid.setValue(el.x, el.y, el.values.get(0));
			}
		}
		// solve ambiguity with the selected algorithm:
		switch (this.strat) {
			case PROBABILISTIC:
				stochasticSearch();
				break;
			case BRUTE:
				depthSearch();
				break;
			case OCCUPANCY:
				occupancyTheorem();
				break;
		}
	}
	
	/**
	 * src: http://pi.math.cornell.edu/~mec/Summer2009/meerkamp/Site/Solving_any_Sudoku_II.html
	 */
	private void occupancyTheorem() {
		for (EmptyCoords el: pencil) {
			// TODO
		}
	}
	
	/**
	 * Try every possible combinations possible until the grid is valid.
	 */
	private void depthSearch() {
		for (EmptyCoords el: pencil) {
			// TODO
		}
	}
	
	/**
	 * Try random combinations until the grid is valid.
	 */
	private void stochasticSearch() {
		// initialize:
		Random prand = new Random();
		boolean flag = true;
		SudokuGrid candidate = null;
		// until we found a solution:
		while (flag) {
			// create a copy of the grid:
			candidate = new SudokuGrid(this.grid);
			// for each empty cell:
			for (EmptyCoords el: pencil) {
				
				System.out.println(el);
				
				// pick a random candidate:
				int selectedValue = el.values.get(prand.nextInt(el.values.size()));
				// assign it to the candidate solution:
				candidate.setValue(el.x, el.y, selectedValue);
			}
			// check validity of the candidate solution:
			if (verificator.verify(candidate.toString()) == 0) {
				// we have found a valid solution to the sudoku grid.
				flag = false;
			}
		}
		// replace the grid by the solved grid:
		this.grid = candidate;
	}
	
	/**
	 * Creates the pencil annotation of the grid.
	 * ie. For each empty space on the grid, determine all possible numbers that could fit.
	 */
	private void createPencilMarks() {
		// parse the grid:
		for (int row = 0; row < SIZE; row++) {
			for (int collumn = 0; collumn < SIZE; collumn++) {
				// if the cell is empty:
				if (grid.isEmptyAt(collumn, row)) {
					
					System.out.println("at coord: "+collumn+", "+row);
					
					// get relevant information:
					int[] parentRow = grid.getRowOf(collumn, row);
					int[] parentCollumn = grid.getCollumnOf(collumn, row);
					int[][] parentSquare = grid.getSquareOf(collumn, row);
					
					System.out.println("parentRow: "+Arrays.toString(parentRow));
					System.out.println("parentCollumn: "+Arrays.toString(parentCollumn));
					System.out.println("parentSquare: "+Arrays.deepToString(parentSquare));
					
					// list missing values:
					List<Integer> values = new ArrayList<Integer>(Arrays.asList(legalValues));
					
					System.out.println("values before -> "+values);
					
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
							if( values.contains((Integer) parentSquare[x][y])) {
								values.remove((Integer) parentSquare[x][y]);
							}
						}
					}
					
					System.out.println("values after -> "+values);
					
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
		
		/*
		@Override
	    public int hashCode() {
	        final int offset = 7;
	        int result = 1;
	        result = offset * result + ((Integer) x).hashCode();
	        result = offset * result + ((Integer) y).hashCode();
	        result = offset * result + values.hashCode();
	        return result;
	    }
		*/
	}
}
