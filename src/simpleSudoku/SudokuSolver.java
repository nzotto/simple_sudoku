package simpleSudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class SudokuSolver {

	private SolvingStrategy strat;
	private SudokuGrid grid;
	static int SIZE = 9;
	static Integer[] legalValues = new Integer[] {1,2,3,4,5,6,7,8,9};
	private Queue<EmptyCoords> pencil;
	private SudokuVerifier verificator;
	
	/**
	 * Constructor.
	 * @param grid, the sudoku grid we wish to solve.
	 * @param strat, a solving strategy. Used to determine which solving algorythm will be used.
	 */
	public SudokuSolver(SudokuGrid grid, SolvingStrategy strat) {
		// assign elements:
		this.grid = grid;
		this.pencil = new LinkedList<EmptyCoords> ();
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
		this.pencil = new LinkedList<EmptyCoords> ();
		this.verificator = new SudokuVerifier();
		this.strat = SolvingStrategy.BRUTE;
	}
	
	public void run() {
		
		System.out.println("\n\n"+"run()");
		
		// solve using the selected algorithm:
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
			case SMART:
				smartSolver();
				break;
		}
	}
	
	private void smartSolver() {
		
		System.out.println("in smartSolver()");
		
		boolean flag = false;
		boolean ambiguous = false;
		boolean notSolved = true;
		
		EmptyCoords[] pencilArray = null;
		ArrayList<EmptyCoords> relevantCoordinates = null;
		
		while (notSolved) {
			
			createPencilMarks();
			
			pencilArray = new EmptyCoords[pencil.size()];
			pencil.toArray(pencilArray);
			
			if (pencil.isEmpty()) {
				
				System.out.println("pencil is Empty");
				break;
				
			}
			
			// for each empty coordinate:
			while (!pencil.isEmpty()) {
				
				EmptyCoords current = pencil.poll();
				
				// get relevant empty coordinates:
				relevantCoordinates = current.getRelevantCoordinates(pencilArray);
				
				// lower flag:
				flag = false;
				
				// for each possible value at current empty coordinates:
				for (int val : current.values) {
					
					// lower flag:
					ambiguous = false;
					
					// this coordinates is the only one containing this possible value among relevant empty coordinates?
					for (EmptyCoords el : relevantCoordinates) {
						if (el.values.contains(val)) {
							// raise flag:
							ambiguous = true;
							break;
						}
					}
					
					// NO:
					if (ambiguous) {
						break;
					}
					// YES:
					else {
						
						System.out.println("Wrote down something!");
						System.out.println(current);
						
						// write found value to the grid:
						grid.setValue(current.x, current.y, val);
						// restart!
						flag = true;
						break;
					}
					
				}
				
				if (flag) {
					// the grid has changed. Restart
					break;
				}
				
			}
			
			// check if we found a solution:
			if (verificator.verify(grid.toString()) == 0) {
				// we have found a valid solution to the sudoku grid.
				notSolved = false;
			}
			
		}
		
		// end
		
	}
	
	/**
	 * src: http://pi.math.cornell.edu/~mec/Summer2009/meerkamp/Site/Solving_any_Sudoku_II.html
	 */
	private void occupancyTheorem() {
		// TODO
	}
	
	/**
	 * Try every possible combinations possible until the grid is valid.
	 */
	private void depthSearch() {
		// TODO
	}
	
	/**
	 * Try random combinations until the grid is valid.
	 */
	private void stochasticSearch() {

		System.out.println("in stochasticSearch()");
		
		// initialize:
		Random prand = new Random();
		boolean flag = true;
		SudokuGrid candidate = null;
		
		// until we found a solution:
		while (flag) {
			
			// create a copy of the grid:
			candidate = new SudokuGrid(this.grid.toString()); // TODO: Change to the clone constructor once it works;

			// annotate all possible values at each empty coordinate:
			createPencilMarks();
			
			System.out.println("pencil annotations:");
			System.out.println(Arrays.toString(pencil.toArray()));
			
			// for each empty coordinate:
			while (!pencil.isEmpty()) {
				EmptyCoords el = pencil.poll();
				
				// fill "obvious" coordinates:
				if (el.values.size() == 1) {
					
					System.out.print("Wrote down the obvious: ");
					System.out.println(el.toString());
					
					// in the real grid:
					grid.setValue(el.x, el.y, el.values.get(0));
					// and in the current candidate:
					candidate.setValue(el.x, el.y, el.values.get(0));
				}
				// choose a random value for "ambiguous" coordinates:
				else {
					// pick a possible value:
					int selectedValue = el.values.get(prand.nextInt(el.values.size()));
					
					System.out.println("At "+"("+el.x+","+el.y+")"+" randomly select -> "+selectedValue);
					
					// assign it to the candidate:
					candidate.setValue(el.x, el.y, selectedValue);
				}
			}
			// check if the candidate is the solution:
			if (verificator.verify(candidate.toString()) == 0) {
				// we have found a valid solution to the sudoku grid.
				flag = false;
			}
		}
		// We found the solution!
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
					// get relevant information:
					int[] parentRow = grid.getRowOf(collumn, row);
					int[] parentCollumn = grid.getCollumnOf(collumn, row);
					int[][] parentSquare = grid.getSquareOf(collumn, row);
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
							if( values.contains((Integer) parentSquare[x][y])) {
								values.remove((Integer) parentSquare[x][y]);
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
	
}
