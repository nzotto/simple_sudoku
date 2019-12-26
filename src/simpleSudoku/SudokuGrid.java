package simpleSudoku;

import java.util.Arrays;
import java.util.Objects;

public class SudokuGrid {

	static int SIZE = 9;
	private int[][] grid;
	
	/* Constructors */
	
	/**
	 * Constructor using a string as an input.
	 * @param startGrid: String, a sudoku grid.
	 * 
	 * Numbers arranged from left to right, and top to bottom where
	 * '0' represents an empty space. Only digits are accepted.
	 */
	public SudokuGrid(String startGrid) throws IllegalArgumentException{
		try {
			if (isValidInput(startGrid)) {
				grid = stringToMatrix(startGrid);
			}
		}
		catch (Exception e){
			throw e
			;
		}
	}
	
	/**
	 * Constructor using an integer matrix as an input.
	 * @param startGrid; int[][] a sudoku grid.
	 * 
	 * '0' represents an empty space. Only digits are accepted.
	 */
	public SudokuGrid(int[][] startGrid) throws IllegalArgumentException{
		try {
			if (isValidInput(startGrid)) {
				grid = startGrid;
			}
		}
		catch (Exception e){
			throw e
			;
		}
	}
	
	/**
	 * Copy constructor.
	 * @return
	 */
	public SudokuGrid (SudokuGrid target) {
		// TODO: doesn't work!
		// The grid is passed by pointer not value.
		this.grid = target.grid;
	}
	
	/* Type conversion functions */
	
	public String toString() {
		return matrixToString(this.grid);
	}
	
	/**
	 * Converts a matrix to a String by passing through it once.
	 * @param matrix: int[][], a valid sudoku grid.
	 * @return The input sudoku grid as a String.
	 */
	private String matrixToString(int[][] input) {
		String res = "";
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				res += input[i][j];
			}
		}
		return res;
	}
	
	/**
	 * converts a String to a matrix by passing through it once.
	 * @param grid: String: a valid sudoku grid.
	 * @return The input sudoku grid as a matrix.
	 */
	private int[][] stringToMatrix(String grid) {
		int[][] res = new int[SIZE][SIZE];
		int token = 0;
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				res[i][j] = Character.getNumericValue(grid.charAt(token));
				token += 1;
			}
		}
		return res;
	}
	
	/* Input checking functions */
	
	/**
	 * Checks validity of an input sudoku grid. 
	 * @param input: int[][] or String, a sudoku grid.
	 * @return true is input contains only digits. false otherwise.
	 * @throws IllegalArgumentException if the input is neither int[][] or String.
	 */
	private boolean isValidInput(Object input) throws IllegalArgumentException {
		// Check input type.
		if (input instanceof int[][]) {
			int[][] candidate = (int[][]) input;
			// check that the input matrix is the correct shape:
			if (candidate.length != SIZE) {
				throw new IllegalArgumentException("Invalid input matrix shape.");
			}
			else {
				if (candidate[0].length != SIZE) {
					throw new IllegalArgumentException("Invalid input matrix shape.");
				}
			}
			// check that the input matrix contains only digits:
			for (int i=0; i<SIZE; i++) {
				for (int j=0; j<SIZE; j++) {
					if (candidate[i][j] <0 || candidate[i][j] > SIZE) {
						throw new IllegalArgumentException("Invalid input value in matrix.");
					}
				}
			}
		}
		else if (input instanceof String) {
			String candidate = (String) input;
			// check that the input string is the right length:
			if(candidate.length() != 81) {
				throw new IllegalArgumentException("Invalid input String length.");
			}
			// check that the input String contains only digits:
			for(int i=0; i < candidate.length(); i++) {
				if((!(Character.isDigit(candidate.charAt(i))))){
					throw new IllegalArgumentException("Invalid input value in String.");
				}
			}
		}
		else {
			throw new IllegalArgumentException("Unsupported input type.");
		}
		return true;
	}
	
	private boolean isValidDigit(int num, String name) throws IllegalArgumentException {
		if (num < 0 || num > SIZE) {
			throw new IllegalArgumentException("Invalid "+name);
		}
		return true;
	}
	
	/* Getters and setters */
	
	public int getValue(int collumn, int row) throws IllegalArgumentException{
		try {
			isValidDigit(collumn, "collumn coordinates");
			isValidDigit(row, "row coordinates");
			return grid[row][collumn];
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	public void setValue(int collumn, int row, int input)  throws IllegalArgumentException{
		try {
			isValidDigit(collumn, "collumn coordinates");
			isValidDigit(row, "row coordinates");
			isValidDigit(input, "intput coordinates");
			grid[row][collumn] = input;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	/* Specifics */

	public boolean isEmptyAt(int collumn, int row) throws IllegalArgumentException{
		try {
			isValidDigit(collumn, "collumn coordinates");
			isValidDigit(row, "row coordinates");
			return grid[row][collumn] == 0;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public int[] getCollumnOf(int collumn, int row) throws IllegalArgumentException{
		try {
			isValidDigit(collumn, "collumn coordinates");
			isValidDigit(row, "row coordinates");
			// get values:
			int[] res = new int[SIZE];
			// iterate over the row:
			for (int i=0; i<SIZE; i++) {
				res[i] = grid[i][collumn];
			}
			return res;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public int[] getRowOf(int collumn, int row) throws IllegalArgumentException{
		try {
			isValidDigit(collumn, "collumn coordinates");
			isValidDigit(row, "row coordinates");
			// get values:
			return grid[row];
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public int[][] getSquareOf(int collumn, int row) throws IllegalArgumentException{
		try {
			isValidDigit(collumn, "collumn coordinates");
			isValidDigit(row, "row coordinates");
			// get values:
			int[][] res = new int[SIZE/3][SIZE/3];
			// identify the subgrid:
			int squareRow = row/(SIZE/3); 
			int squareCollumn = collumn/(SIZE/3);
			// indexes for parsing the result matrix:
			int x = -1;
			int y = -1;
			// construct the appropriate subgrid:
			for (int i=squareRow*(SIZE/3); i<squareRow*(SIZE/3)+3; i++) {
				// update index
				x+=1;
				y=-1;
				for(int j=squareCollumn*(SIZE/3); j<squareCollumn*(SIZE/3)+3; j++) {
					// update index:
					y+=1;
					res[x][y] = grid[i][j];
				}
			}
			return res;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	/* Overwriten functions */
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof SudokuGrid)){
			return false;
		}
		// type conversion:
		SudokuGrid candidate = (SudokuGrid) other;
		
		//return Arrays.equals(this.grid, candidate.grid);
		return this.toString().equals(candidate.toString()); // TODO: POURQUOI?
	}
}
