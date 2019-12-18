package simpleSudokuTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import simpleSudoku.SudokuGrid;
import simpleSudoku.SudokuSolver;

public class SudokuSolverTest {

	/* Test Constructors */
	@Test
	public void SudokuSolver_Constructor() {
		SudokuGrid grid = new SudokuGrid("417360825632158947958724316825437169791586432346912758289643571573291684164875293"); //1 value missing: 9
		
		SudokuSolver solver = new SudokuSolver(grid);
		// Constructor doesn't fail.
		assertTrue(true);
	}
	
	/* test simple cases */
	@Test
	public void SudokuSolver_OneMissingValue() {
		SudokuGrid solution = new SudokuGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		SudokuGrid grid = new SudokuGrid("417360825632158947958724316825437169791586432346912758289643571573291684164875293"); //1 value missing: 9
		
		SudokuSolver solver = new SudokuSolver(grid);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	@Test
	public void SudokuSolver_DependentValues() {
		// TODO
	}
	
	@Test
	public void SudokuSolver_SameRow() {
		// TODO
	}
	
	@Test
	public void SudokuSolver_SameCollumn() {
		// TODO
	}
	
	@Test
	public void SudokuSolver_SameSquare() {
		// TODO
	}
}
