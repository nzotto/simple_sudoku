package simpleSudokuTests;

import org.junit.Test;
import static org.junit.Assert.*;

import simpleSudoku.SudokuVerifier;

public class SudokuVerifierTest {

// A correct Sudoku string: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
// An incorrect Sudoku string: 123456789912345678891234567789123456678912345567891234456789123345678912234567891

	@Test
	public void testVerify_CorrectSolution() {
		SudokuVerifier sv = new SudokuVerifier();
		int test = sv.verify("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		assertEquals(0, test);
	}
	
	@Test
	public void testVerify_WrongSolution() {
		SudokuVerifier sv = new SudokuVerifier();
		int test = sv.verify("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
		assertEquals(-2, test);
	}
	
	@Test
	public void testVerify_LESSTHAN81DIGITS() {
		SudokuVerifier sv = new SudokuVerifier();
		int test = sv.verify("123456789");
		assertEquals(-5, test);
	}
	
	@Test
	public void testVerify_MORETHAN81DIGITS() {
		SudokuVerifier sv = new SudokuVerifier();
		int test = sv.verify("123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789");
		assertEquals(-5, test);
	}
	
	@Test
	public void testVerify_WrongFormatSolution() {
		SudokuVerifier sv = new SudokuVerifier();
		int test = sv.verify("12345678991234567889123456778a123456678912345567891234456789123345678912234567891");
		assertEquals(-1, test);
	}

}
