package sudoku;

public interface SudokuSolver {
	/**
	 * Sets the digit number in the box row, col.
	 * 
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	void setNumber(int row, int col, int number);

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
	boolean trySetNumber(int row, int col, int number);

	/**
	 * Returns the number at the row "row" and column "col".
	 * 
	 * @param row		The row
	 * @param col		The column
	 * @return 			The number at row, col
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	int getNumber(int row, int col);

	/**
	 * Removes the number at the row "row" and column "col";
	 * 
	 * @param row		The row
	 * @param col		The column
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	void removeNumber(int row, int col);

	/**
	 * Removes all the numbers from the sudoku.
	 */
	void clear();

	/**
	 * Solves the soduku and returns true if it is solvable. 
	 * 
	 * @return			True if the soduku is solvable
	 */
	boolean solve();

	/**
	 * Returns the sudoku board.
	 * 
	 * @return			All the numbers in the sudoku.
	 */
	int[][] getNumbers();

	/**
	 * Assigns all the digits in the matrix numbers to the sudoku.
	 * 
	 * @param			An int-matrix
	 * @throws IllegalArgumentException if not all numbers in [0..9]
	 **/
	void setNumbers(int[][] numbers);
}
