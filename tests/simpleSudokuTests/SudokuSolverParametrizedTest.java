package simpleSudokuTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import simpleSudoku.SudokuGrid;
import simpleSudoku.SudokuSolver;

public class SudokuSolverParametrizedTest {
	// correct solution: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
	
	/* test simple cases */
	@Test
	public void SudokuSolver_OneMissingValue() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid(""
				+ "417369825"
				+ "632158947"
				+ "908724316"
				+ "825437169"
				+ "791586432"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "164875293"); //1 value missing: 5 at 1,2
		
		SudokuSolver solver = new SudokuSolver(grid);
		solver.run();
		
		System.out.println();
		System.out.println(solution.toString());
		System.out.println(solver.getGrid().toString());
		
		
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_DependentValues() {
		// TODO
	}
	
	@Test
	public void SudokuSolver_SameRow() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid(""
				+ "417369825"
				+ "632158947"
				+ "908704310"
				+ "825437169"
				+ "791586432"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "164875293"); 
		/* 
		 * 3 values missing: 
		 * - 5 at 1,2
		 * - 6 at 8,2
		 * - 2 at 4,2
		 */
		
		SudokuSolver solver = new SudokuSolver(grid);
		solver.run();
		
		System.out.println();
		System.out.println(solution.toString());
		System.out.println(solver.getGrid().toString());
		
		
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_SameCollumn() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid(""
				+ "417369825"
				+ "602158947"
				+ "958724316"
				+ "805437169"
				+ "791586432"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "104875293"); 
		/* 3 value missing: 
		 * - 3 at 1,1
		 * - 2 at 1,3
		 * - 6 at 1,8
		 */
		
		SudokuSolver solver = new SudokuSolver(grid);
		solver.run();
		
		System.out.println();
		System.out.println(solution.toString());
		System.out.println(solver.getGrid().toString());
		
		
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_SameSquare() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid(""
				+ "417369825"
				+ "632158947"
				+ "958724316"
				+ "825437169"
				+ "791586432"
				+ "346912758"
				+ "289643071"
				+ "573291604"
				+ "164875290"); 
		/* 
		 * 1 value missing: 
		 * - 5 at 6,6
		 * - 8 at 7,7
		 * - 3 at 8,8
		 */
		
		SudokuSolver solver = new SudokuSolver(grid);
		solver.run();
		
		System.out.println();
		System.out.println(solution.toString());
		System.out.println(solver.getGrid().toString());
		
		
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
}
