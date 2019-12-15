package simpleSudokuTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import simpleSudoku.SudokuGrid;
import simpleSudoku.SudokuSolver;

public class SudokuSolverTest {

	/* Test Constructors */
	@Test
	public void Sudokugrid_StringConstructor() {
		SudokuGrid grid = new SudokuGrid("417360825632158947958724316825437169791586032346912758289643571573291684164875293");
		
		SudokuSolver solver = new SudokuSolver(grid);
		// Constructor doesn't fail:
		assertTrue(true);
	}
}