package simpleSudokuTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import simpleSudoku.SolvingStrategy;
import simpleSudoku.SudokuGrid;
import simpleSudoku.SudokuSolver;

public class SudokuSolverParametrizedTest {
	// correct solution: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
	
	/* Test simple cases ... */
	
	/* ... use probabilistic strategy */
	@Test
	public void SudokuSolver_OneMissingValueProbabilistic() {
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
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.PROBABILISTIC);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_MissingSquareProbabilistic() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid(""
				+ "417369825"
				+ "632158947"
				+ "958724316"
				+ "825000169"
				+ "791000432"
				+ "346000758"
				+ "289643571"
				+ "573291684"
				+ "164875293"); 
		/* 1 square missing:
		 * 437
		 * 586
		 * 912
		 */
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.PROBABILISTIC);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_SameRowProbabilistic() {
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
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.PROBABILISTIC);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_SameCollumnProbabilistic() {
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
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.PROBABILISTIC);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_SameSquareProbabilistic() {
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
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.PROBABILISTIC);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	/* ... use smart strategy */
	@Test
	public void SudokuSolver_OneMissingValueSmart() {
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
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.SMART);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_MissingSquareSmart() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid(""
				+ "417369825"
				+ "632158947"
				+ "958724316"
				+ "825000169"
				+ "791000432"
				+ "346000758"
				+ "289643571"
				+ "573291684"
				+ "164875293"); 
		/* 1 square missing:
		 * 437
		 * 586
		 * 912
		 */
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.SMART);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
}
