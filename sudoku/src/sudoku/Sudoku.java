package sudoku;

public class Sudoku implements SudokuSolver {

	public static void main (String[] args) {
		Sudoku sudoku = new Sudoku();

		//Tests:
//		int[][] matrix = new int[][] {
//			{0, 0, 6, 0, 0, 9, 0, 6, 2 },
//			{0, 0, 0, 0, 0, 0, 0, 0, 5 },
//			{1, 0, 2, 5, 0, 0, 0, 0, 0 },
//			{0, 0, 0, 2, 1, 0, 0, 9, 0 },
//			{0, 5, 0, 0, 0, 0, 6, 0, 0 },
//			{6, 0, 0, 0, 0, 0, 0, 2, 8 },
//			{4, 1, 0, 6, 0, 8, 0, 0, 0 },
//			{8, 6, 0, 0, 3, 0, 1, 0, 0 },
//			{0, 0, 0, 0, 0, 0, 4, 0, 0 },
//		};
//		sudoku.print();
//		sudoku.setNumbers(matrix);
//		sudoku.print();

//
//		sudoku.setNumbers(matrix);
//		sudoku.print();
//		sudoku.solve();
//		sudoku.print();

		new SudokuController();
		}

	//test'

	//-----------------------------------------------------------------------------------//



	private int [][] sudokuMatrix = new int [9][9]; 	//Deklares the sudoku board as a 9x9 matrix consisting of Integers
	private boolean checkStartBoard = true;		//Deklares the boolean checkStartBoard that indicates if the
												//starting board complies with the sudoku rules



	/**
	 * Sets the digit number in the box row, col.
	 *
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	@Override
	public void setNumber(int row, int col, int number) {
		checkRowCol(row, col);		//Call the private help method checkRowCol to determine that row & col are inside [0..8]
		checkNumber(number);		//Call the private help method checkNumber to determine that number is inside [1..9]
		sudokuMatrix[row][col] = number;		//Assigns number at row, col in the sudoku matrix

	}

	/**
	 * Checks if the digit "number" can be assigned to the row "row" and the column
	 *  "col". If it is not possible according to the sudoku rules return false.
	 *
	 * @param row		The row
	 * @param col		The column
	 * @param number	The digit to insert in row, col
	 * @return			True if number can be assigned to row and col according to
	 * 					the rules. Otherwise false.
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	@Override
	public boolean trySetNumber(int row, int col, int number) {
		checkRowCol(row, col);		//Call the private help method checkRowCol to determine that row & col are inside [0..8]
		checkNumber(number);		//Call the private help method checkNumber to determine that number is inside [1..9]

		//Check that the row does not already contain number
		for(int i = 0; i <= 8; i++) {
			if(number == sudokuMatrix[row][i]) {
				return false;
			}
		}

		//Check that the column does not already contain number
		for(int i = 0; i <= 8; i++) {
			if(number == sudokuMatrix[i][col]) {
				return false;
			}
		}

		//Check that the region does not already contain number
		int startRow = row - (row % 3);		//The regions starting row
		int startCol = col - (col % 3);		//The regions starting column

		//Checks the region
		for(int i = 0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				if(number == sudokuMatrix[startRow + i][startCol + j]) {
					return false;
				}
			}
		}
		return true;				//if false has not been returned in the above rule-checks it is ok to set the number and true is returned
	}

	/**
	 * Returns the number at the row "row" and column "col".
	 *
	 * @param row		The row
	 * @param col		The column
	 * @return 			The number at row, col
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	@Override
	public int getNumber(int row, int col) {
		checkRowCol(row, col);			//Call the private help method checkRowCol to determine that row & col are inside [0..8]
		return sudokuMatrix[row][col];		//returns the number at row, col
	}

	/**
	 * Removes the number at the row "row" and column "col";
	 *
	 * @param row		The row
	 * @param col		The column
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	@Override
	public void removeNumber(int row, int col) {
		checkRowCol(row, col);			//Call the private help method checkRowCol to determine that row & col are inside [0..8]
		sudokuMatrix[row][col] = 0;		//Removes the number at row, col
	}

	/**
	 * Removes all the numbers from the sudoku.
	 */
	@Override
	public void clear() {
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col++) {
				sudokuMatrix[row][col] = 0;
			}
		}
	}

	/**
	 * Solves the soduku and returns true if it is solvable.
	 *
	 * @return			True if the soduku is solvable
	 */
	@Override
	public boolean solve() {
		if(checkStartBoard == true) { 	//if the start board is rule compliant call the private help method solve otherwise return false
			return solve(0, 0);		//calls private method solve with starting position at upper left corner, [0][0]
		}
		return false;
	}

	private boolean solve(int row, int col){
		if(row == 9) {			//if row is = 9 the sudoku has been solved
			return true;
		}

		//Calculates the following row and col
		int newRow = row;
		int newCol = col;
		if(col >= 8) {
			newCol = 0;
			newRow = newRow + 1;
		}
		else {
			newCol = newCol + 1;
			newRow = row;
		}

		//Rekursive backtracking method for solving the sudoku:
		if(sudokuMatrix[row][col] == 0) {
			for(int number = 1; number <= 9; number ++) {
				if(trySetNumber(row, col, number) == true){
					setNumber(row, col, number);
					if(solve(newRow, newCol) == true) {
						return true;
					}
					else {
						removeNumber(row, col);
					}
				}
			}
		}
		else {
			return solve(newRow, newCol);
		}
		return false;
	}

	/**
	 * Returns the sudoku board.
	 *
	 * @return			All the numbers in the sudoku.
	 */
	@Override
	public int[][] getNumbers() {
		int [][] tempMatrix = new int [9][9];
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col++) {
				tempMatrix[row][col] = sudokuMatrix[row][col];
			}
		}
		return tempMatrix;
	}

	/**
	 * Assigns all the digits in the matrix numbers to the sudoku.
	 *
	 * @param			An int-matrix
	 * @throws IllegalArgumentException if not all numbers in [0..9]
	 **/
	@Override
	public void setNumbers(int[][] numbers) {
		checkStartBoard = true;		//Assume that the starting board is rule comliant
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col ++) {
				if(numbers[row][col] != 0 && trySetNumber(row, col, numbers[row][col]) == true) {
					setNumber(row, col, numbers[row][col]);		//Sets the number at row, col if rule compliant
				}
				else if(numbers[row][col] != 0 && trySetNumber(row, col, numbers[row][col]) == false) {
					setNumber(row, col, numbers[row][col]);		//Sets the number at row, col even though it is not rule compliant
					checkStartBoard = false;					//Since not rule compliant set checkStartBoard false
				}
			}
		}
	}

	//Private help method to check that row, col is inside [0..8], otherwise throw IllegalArgumentException
	private void checkRowCol(int row, int col) {
		if(row <= 8 && row >= 0 && col <= 8 && col >= 0) {
			return;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	//Private help method to check that number is inside [1..9], otherwise throw IllegalArgumentException
	private void checkNumber(int number) {
		if(number >= 1 && number <= 9) {
			return;
		}
		else {
			throw new IllegalArgumentException();
		}
	}



	//-----------------------------------------------------------------------------------//



	//Print-method for testing purposes. Not used in the sudoku-program.
	/*
	public void print() {
		for(int i = 0; i <= 8; i++) {
			for(int j = 0; j <= 8; j++) {
				System.out.print(sudokuMatrix[i][j] + "  ");
			}
			System.out.println();
		}
	}
	*/
}
