package simpleSudokuTests;

import org.junit.Test;

import simpleSudoku.SudokuVerifier;

import static org.junit.Assert.*;

public class SudokuVerifierParametrizedTest {
	
	//Test for CheckDigits() Method
	@Test
	public void CheckDigits_False() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkDigits("a231232");
		assertEquals(false,test);
	}
	
	@Test
	public void CheckDigits_True() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkDigits("2231232");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckDigits_True_TestCorrectString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkDigits("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckDigits_False_ZeroValue() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkDigits("0231232");
		assertEquals(false,test);
	}
	
	
	//Test for CheckRowsGrid() Method
	@Test
	public void CheckRowsGrid_True() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkRowsGrid("123456789");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckRowsGrid_False() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkRowsGrid("113456789");
		assertEquals(false,test);
	}
	
	@Test
	public void CheckRowsGrid_True_2RowsGrids() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkRowsGrid("123456789234567891");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckRowsGrid_False_2Rowsgrids() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkRowsGrid("123456789113456789");
		assertEquals(false,test);
	}
	
	@Test
	public void CheckRowsGrid_True_TestCorrectString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkRowsGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckRowsGrid_True_TestWrongString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkRowsGrid("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
		assertEquals(true,test);
	}
	
	//Test for checkSubGrid() Method
	@Test
	public void CheckSubGrid_True_String() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkSubGrid("123456789456789123789123456");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckSubGrid_True_TestCorrectString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkSubGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckSubGrid_False_TestWrongString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkSubGrid("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
		assertEquals(false,test);
	}
	
	//Test for checkColumnsGrid() Method
	@Test
	public void CheckColumnsGrid_True_TestCorrectString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkColumnsGrid("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckColumnsGrid_True_TestWrongString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkColumnsGrid("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
		assertEquals(true,test);
	}
	
	@Test
	public void CheckColumnsGrid_False_TestWrongString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkColumnsGrid("123456789123456789123456789123456789123456789123456789123456789123456789123456789");
		assertEquals(false,test);
	}
	
	@Test
	public void CheckColumnsGrid_False_Test2RowsString() {
		SudokuVerifier sv = new SudokuVerifier();
		boolean test = sv.checkColumnsGrid("123456789123456789");
		assertEquals(false,test);
	}
	
}
