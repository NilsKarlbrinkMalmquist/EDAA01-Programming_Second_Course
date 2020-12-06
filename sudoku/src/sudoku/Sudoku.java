package sudoku;

public class Sudoku implements SudokuSolver {
	
	public static void main (String[] args) {
		Sudoku sudoku = new Sudoku();
		
		sudoku.setNumber(0, 2, 8);
		sudoku.setNumber(0, 5, 9);
		sudoku.setNumber(0, 7, 6);
		sudoku.setNumber(0, 8, 2);
		
		sudoku.setNumber(1, 8, 5);
		
		sudoku.setNumber(2, 0, 1);
		sudoku.setNumber(2, 2, 2);
		sudoku.setNumber(2, 3, 5);
		
		sudoku.setNumber(3, 3, 2);
		sudoku.setNumber(3, 4, 1);
		sudoku.setNumber(3, 7, 9);
		
		sudoku.setNumber(4, 1, 5);
		sudoku.setNumber(4, 6, 6);
		
		sudoku.setNumber(5, 0, 6);
		sudoku.setNumber(5, 7, 2);
		sudoku.setNumber(5, 8, 8);
		
		sudoku.setNumber(6, 0, 4);
		sudoku.setNumber(6, 1, 1);
		sudoku.setNumber(6, 3, 6);
		sudoku.setNumber(6, 5, 8);
		
		sudoku.setNumber(7, 0, 8);
		sudoku.setNumber(7, 1, 6);
		sudoku.setNumber(7, 4, 3);
		sudoku.setNumber(7, 6, 1);
		
		sudoku.setNumber(8, 6, 4);
		
		sudoku.print();
//		System.out.println(sudoku.trySetNumber(1, 1, 9));
//		System.out.println(sudoku.trySetNumber(1, 1, 5));
//		System.out.println(sudoku.trySetNumber(0, 2, 1));
//		System.out.println(sudoku.trySetNumber(1, 1, 4));
		sudoku.trySetNumber(1, 1, 8);
//		sudoku.clear();
		System.out.println();
//		sudoku.print();
		sudoku.solve();
		sudoku.print();
		}
	
	
	
	//-----------------------------------------------------------------------------------//
	
	
	
	public int [][] sudokuMatrix = new int [9][9];
	
	public Sudoku() {
		
	}
	
	//Assigns the number to the given row and column
	@Override
	public void setNumber(int row, int col, int number) {
		check(row, col, number);
		sudokuMatrix[row][col] = number;
	}
	
	//Check if it's ruels compliant to assign number at the given row and column.
	@Override
	public boolean trySetNumber(int row, int col, int number) {
		//Check the row
		for(int i = 0; i <= 8; i++) {
			if(number == sudokuMatrix[row][i]) {
				return false;
			}
		}
		
		//Check the column
		for(int i = 0; i <= 8; i++) {
			if(number == sudokuMatrix[i][col]) {
				return false;
			}
		}		
		
		//Check the region/box
		int startRow = row - (row % 3);
		int startCol = col - (col % 3);
		
		for(int i = 0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				if(number == sudokuMatrix[startRow + i][startCol + j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int getNumber(int row, int col) {
		//int number = [row][col];
		return sudokuMatrix[row][col];
	}
	
	@Override
	public void removeNumber(int row, int col) {
		//check(row, col);
		sudokuMatrix[row][col] = 0;	
	}
	
	@Override
	public void clear() {
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col++) {
				sudokuMatrix[row][col] = 0;
			}
		}
	}
	
	@Override
	public boolean solve() {
		return solve(0, 0);
	}
	
	private boolean solve(int row, int col){
		if(sudokuMatrix[8][8] != 0) {
			return true;
		}
		
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
	
	@Override
	public void setNumbers(int[][] numbers) {
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col ++) {
				setNumber(row, col, numbers[row][col]);
			}
		}
		
	}
	
	private void check(int row, int col, int number) {
		if(row <= 8 && row >= 0 && col <= 8 && col >= 0 && number >= 1 && number <= 9) {
			return;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	
	
	
	//Egen metod, ska nog ersättas av getNumbers
	public void print() {
		for(int i = 0; i <= 8; i++) {
			for(int j = 0; j <= 8; j++) {
				System.out.print(sudokuMatrix[i][j] + "  ");
			}
			System.out.println();
		}
	}
	

}
