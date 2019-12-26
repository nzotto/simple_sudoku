package simpleSudokuTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import simpleSudoku.SudokuGrid;

@SuppressWarnings("unused")
public class SudokuGridTest {
	
	/* Test Constructors */
	
	@Test
	public void Sudokugrid_StringConstructor() {
		SudokuGrid grid = new SudokuGrid("417360825632158947958724316825437169791586032346912758289643571573291684164875293");
		// Constructor doesn't fail:
		assertTrue(true);
	}
	
	@Test
	public void Sudokugrid_MatrixConstructor() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		// Constructor doesn't fail:
		assertTrue(true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_MatrixConstructorWrongSize() {
		int[][] matrix = new int[9][8];
		for (int i=0; i<9; i++) {
			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		// Constructor fails
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_MatrixConstructorWrongValue() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {
			Arrays.fill(matrix[i], i);
		}
		matrix[5][6] = 99;
		
		SudokuGrid grid = new SudokuGrid(matrix);
		// Constructor fails
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_StringConstructorWrongLength() {
		SudokuGrid grid = new SudokuGrid("17360825632158947958724316825437169791586032346912758289643571573291684164875293");
		// Constructor fails:
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_StringConstructorValue() {
		SudokuGrid grid = new SudokuGrid("a17360825632158947958724316825437169791586032346912758289643571573291684164875293");
		// Constructor fails:
	}
	
	/* Test type conversion functions*/
	
	@Test
	public void Sudokugrid_toString() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		assertEquals(grid.toString(), "000000000111111111222222222333333333444444444555555555666666666777777777888888888");
	}
	
	@Test
	public void Sudokugrid_toStringBis() {
		String input = "111111111222222222333333333444444444555555555666666666777777777888888888999999999";
		
		SudokuGrid grid = new SudokuGrid(input);
		
		assertEquals(grid.toString(), input);
	}
	
	/* Test getters and setters*/
	
	@Test
	public void Sudokugrid_getValue() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		assertEquals(grid.getValue(5, 5), matrix[5][5]);
	}
	
	@Test
	public void Sudokugrid_setValue() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		grid.setValue(5, 5, 0);
		
		assertEquals(grid.getValue(5, 5), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getValueWrongRow() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getValue(5, -5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getValueWrongCollumn() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getValue(-5, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_setValueWrongValue() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.setValue(5, 5, -14);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_setValueWrongCollumn() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.setValue(-5, 5, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_setValueWrongRow() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.setValue(5, -5, 0);
	}
	
	/*Test specifics*/
	@Test
	public void Sudokugrid_isEmptyAt() {
		SudokuGrid grid = new SudokuGrid(""
				+ "417360825"
				+ "632158947"
				+ "958724316"
				+ "825437169"
				+ "791586032"
				+ "346912758"
				+ "289643571"
				+ "573290684"
				+ "164875293");
		
		
		assertFalse(grid.isEmptyAt(5, 5));
	}
	
	@Test
	public void Sudokugrid_isEmptyAtBis() {
		SudokuGrid grid = new SudokuGrid(""
				+ "417360825"
				+ "632158947"
				+ "958724316"
				+ "825437169"
				+ "791586032"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "164875293");
		
		
		assertTrue(grid.isEmptyAt(5, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_isEmptyAtWrongRow() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.isEmptyAt(5, -5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_isEmptyAtWrongCollumn() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.isEmptyAt(-5, 5);
	}
	
	@Test
	public void Sudokugrid_getRowOf() {
		SudokuGrid grid = new SudokuGrid(""
				+ "417360825"
				+ "632158947"
				+ "958724316"
				+ "825437169"
				+ "791586032"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "164875293");
		
		assertArrayEquals(grid.getRowOf(1, 2), new int[] {9,5,8,7,2,4,3,1,6});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getRowOfWrongCollumn() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getRowOf(-5, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getRowOfWrongRow() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getRowOf(5, -5);
	}
	
	@Test
	public void Sudokugrid_getCollumnOf() {
		SudokuGrid grid = new SudokuGrid(""
				+ "417360825"
				+ "632158947"
				+ "958724316"
				+ "825437169"
				+ "791586032"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "164875293");
		
		assertArrayEquals(grid.getCollumnOf(1, 3), new int[] {1,3,5,2,9,4,8,7,6});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getCollumnOfWrongCollumn() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getCollumnOf(-5, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getCollumnOfWrongRow() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getCollumnOf(5, -5);
	}
	
	@Test
	public void Sudokugrid_getSquareOf() {
		SudokuGrid grid = new SudokuGrid(""
				+ "417360825"
				+ "632158947"
				+ "958724316"
				+ "825437169"
				+ "791586032"
				+ "346912758"
				+ "289643571"
				+ "573291684"
				+ "164875293");

		assertArrayEquals(grid.getSquareOf(7, 8), new int[][] {{5,7,1},{6,8,4},{2,9,3}});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getSquareOfWrongCollumn() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getSquareOf(-5, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Sudokugrid_getSquareOfWrongRow() {
		int[][] matrix = new int[9][9];
		for (int i=0; i<9; i++) {

			Arrays.fill(matrix[i], i);
		}
		
		SudokuGrid grid = new SudokuGrid(matrix);
		
		grid.getSquareOf(5, -5);
	}
	
	/* */
	
	@Test
	public void Sudokugrid_TestEquals() {
		String str = "417369825632158947958724316825437169791586032346912758289643571573291684164875293";
		
		SudokuGrid grid1 = new SudokuGrid(str);
		SudokuGrid grid2 = new SudokuGrid(str);
		
		assertEquals(grid1, grid2);
	}
	
	@Test
	public void Sudokugrid_TestEqualsBis() {
		String str1 = "417369825632158947958724316825437169791586032346912758289643571573291684164875293";
		String str2 = "000369825632158947958724316825437169791586032346912758289643571573291684164875293";
		
		SudokuGrid grid1 = new SudokuGrid(str1);
		SudokuGrid grid2 = new SudokuGrid(str2);
		
		assertNotEquals(grid1, grid2);
	}
}
