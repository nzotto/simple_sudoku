package simpleSudoku;

/**
 * Checks that a sudoku solution is valid.
 * The candidate solution is represented by a String of 81 numerical characters.
 * @author Zotto
 */
public class SudokuVerifier {
	
	/**
	 * Main function that checks if the proposed solution is correct.
	 * 
	 * @param candidateSolution, a String: The proposed solution.
	 * @return an Integer: 0 if the solution is correct or a flag indicating the error commited.
	 */
	public int verify(String candidateSolution) {
		int flagError = 0;
		if(candidateSolution.length() == 81) {
			if(checkDigits(candidateSolution)) {
				if(checkSubGrid(candidateSolution)) {
					if(checkRowsGrid(candidateSolution)) {
						if(checkColumnsGrid(candidateSolution)) {
						}
						else
							flagError = -4;
					}
					else
						flagError = -3;
				}
				else
					flagError = -2;
			}
			else
			 flagError = -1;
		}
		else
			flagError = -5;
			
		// returns 0 if the candidate solution is correct
		return flagError;
	}
	
	/**
	 * Check for non numerical values in the proposed solution.
	 * 
	 * @param candidateSolution, a String: The proposed solution.
	 * @return a boolean indicating if the condition is cleared or not.
	 */
	public boolean checkDigits(String candidateSolution) {
		boolean flag = true;
		for(int i=0; i < candidateSolution.length(); i++) {
			if((!(Character.isDigit(candidateSolution.charAt(i)))) || (Character.getNumericValue(candidateSolution.charAt(i)) == 0))
			{
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * Check for repeating numbers in each row.
	 * 
	 * @param candidateSolution, a String: The proposed solution.
	 * @return a boolean indicating if the condition is cleared or not.
	 */
	public boolean checkRowsGrid(String candidateSolution) {
		boolean flag = true;
		for (int k = 0; k < candidateSolution.length(); k+=9) {
			for (int i = k; i < k+9; i++) { 
				for (int j = i + 1; j < k+9; j++) {
					 if (candidateSolution.charAt(i) == candidateSolution.charAt(j)) 
			                flag = false; 
				}
			}
		}
		return flag;
	}
	
	/**
	 * Check for repeating numbers in each sub-grid.
	 * 
	 * @param candidateSolution, a String: The proposed solution.
	 * @return a boolean indicating if the condition is cleared or not.
	 */
	public boolean checkSubGrid(String candidateSolution) {
		boolean flag = true;
		
		for(int x=0; x < candidateSolution.length(); x+=27) {
			for(int j=x; j < x+8; j+=3) {
				String currentSubGrid = "";
				for(int k=j; k < j+26; k= k+9) {
					for (int i=k; i < k+3; i++) {
						currentSubGrid += candidateSolution.charAt(i);
					}
				}
				//System.out.println(currentSubGrid);
				if(!checkRowsGrid(currentSubGrid))
					flag = false;
			}
		}	
		return flag;
	}
	
	/**
	 * Check for repeating numbers in each column.
	 * 
	 * @param candidateSolution, a String: The proposed solution.
	 * @return a boolean indicating if the condition is cleared or not.
	 */
	public boolean checkColumnsGrid(String candidateSolution) {
		boolean flag = true;
		for(int x=0; x < 9; x++) {
			for(int y=x; y < candidateSolution.length(); y+=9) {
				for(int j = y+9; j < candidateSolution.length(); j+=9) {
					if (candidateSolution.charAt(y) == candidateSolution.charAt(j)) 
		                flag = false; 
				}
			}
		}
		return flag;
	}
	
}
