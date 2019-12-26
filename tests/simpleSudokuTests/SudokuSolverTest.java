package simpleSudokuTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import simpleSudoku.SolvingStrategy;
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
	
	
	@Test
	public void SudokuSolver_HalfSolvedGrid() {
		SudokuGrid grid = new SudokuGrid(""
				+ "000002469"
				+ "274169358"
				+ "968534172"
				+ "600003205"
				+ "800005600"
				+ "520601700"
				+ "436928517"
				+ "182457936"
				+ "700316824"); 
		
		SudokuGrid solution = new SudokuGrid(""
				+ "351782469"
				+ "274169358"
				+ "968534172"
				+ "617893245"
				+ "843275691"
				+ "529641783"
				+ "436928517"
				+ "182457936"
				+ "795316824"); 
		
		SudokuSolver solver = new SudokuSolver(grid, SolvingStrategy.SMART);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
	
	
	/* Difficult grids */ 
	
	@Test
	public void SudokuSolver_AmbiguousGrid() {
		SudokuGrid grid = new SudokuGrid(""
				+ "008247300"
				+ "000000000"
				+ "040508020"
				+ "607010903"
				+ "003000200"
				+ "800000005"
				+ "001090800"
				+ "090020060"
				+ "006000100"); 
		
		SudokuGrid solution = new SudokuGrid(""
				+ "568247391"
				+ "732169548"
				+ "149538726"
				+ "657412983"
				+ "913685274"
				+ "824973615"
				+ "471396852"
				+ "395821467"
				+ "286754139"); 
		
		SudokuSolver solver = new SudokuSolver(grid);
		solver.run();
		
		// the grid is properly solved: 
		assertEquals(solution, solver.getGrid());
	}
}
