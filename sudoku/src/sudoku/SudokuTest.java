package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {
	
	private SudokuSolver sudoku;
	

	@BeforeEach
	void setUp() throws Exception {
		sudoku = new Sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		sudoku = null;
	}

	@Test
	void testSetNumber() {
		sudoku.setNumber(0, 0, 3);
		sudoku.setNumber(5, 8, 7);
		sudoku.setNumber(6, 4, 1);
		
		assertEquals(3, sudoku.getNumber(0, 0), "Number should be 3");
		assertEquals(7, sudoku.getNumber(5, 8), "Number should be 7");
		assertEquals(1, sudoku.getNumber(6, 4), "Number should be 1");
		
		assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(10, 8, 9));
		assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(8, 10, 9));
		assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(5, 6, 10));
		assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(5, 6, 0));
	}

	@Test
	void testTrySetNumber() {
		assertTrue(sudoku.trySetNumber(0, 0, 1), "Should be true");
		sudoku.setNumber(0, 0, 1);
		assertFalse(sudoku.trySetNumber(0, 2, 1), "Should be false");
		assertFalse(sudoku.trySetNumber(2, 0, 1), "Should be false");
		assertFalse(sudoku.trySetNumber(2, 2, 1), "Should be false");
		
		assertThrows(IllegalArgumentException.class, () -> sudoku.trySetNumber(10, 8, 9));
		assertThrows(IllegalArgumentException.class, () -> sudoku.trySetNumber(8, 10, 9));
		assertThrows(IllegalArgumentException.class, () -> sudoku.trySetNumber(5, 6, 10));
		assertThrows(IllegalArgumentException.class, () -> sudoku.trySetNumber(5, 6, 0));
	}

	@Test
	void testGetNumber() {
		sudoku.setNumber(0, 0, 3);
		sudoku.setNumber(5, 8, 7);
		sudoku.setNumber(6, 4, 1);
		assertEquals(3, sudoku.getNumber(0, 0), "Number should be 3");
		assertEquals(7, sudoku.getNumber(5, 8), "Number should be 7");
		assertEquals(1, sudoku.getNumber(6, 4), "Number should be 1");
		assertThrows(IllegalArgumentException.class, () -> sudoku.getNumber(10, 1));
	}

	@Test
	void testRemoveNumber() {
		sudoku.setNumber(0, 0, 3);
		sudoku.setNumber(5, 8, 7);
		sudoku.setNumber(6, 4, 1);
		
		sudoku.removeNumber(0, 0);
		sudoku.removeNumber(5, 8);
		sudoku.removeNumber(6, 4);
		assertEquals(0, sudoku.getNumber(0, 0), "Number should be 0");
		assertEquals(0, sudoku.getNumber(5, 8), "Number should be 0");
		assertEquals(0, sudoku.getNumber(6, 4), "Number should be 0");
		
		assertThrows(IllegalArgumentException.class, () -> sudoku.removeNumber(10, 1));
	}

	@Test
	void testClear() {
		sudoku.setNumber(0, 0, 3);
		sudoku.setNumber(5, 8, 7);
		sudoku.setNumber(6, 4, 1);
		
		sudoku.clear();
		assertEquals(0, sudoku.getNumber(0, 0), "Number should be 0");
		assertEquals(0, sudoku.getNumber(5, 8), "Number should be 0");
		assertEquals(0, sudoku.getNumber(6, 4), "Number should be 0");
	}

	@Test
	void testSolve() {
		//Test empty sudoku:
		int[][] emptySudoku = new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		sudoku.setNumbers(emptySudoku);
		assertTrue(sudoku.solve());
		
		//Test example sudoku:
		sudoku.clear();
		int[][] testSudoku = new int[][] {
			{0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		sudoku.setNumbers(testSudoku);
		assertTrue(sudoku.solve());
		
		//Test unsolvable sudoku 1:
		sudoku.clear();
		int[][] unsolvableSudoku1 = new int[][] {
			{2, 0, 0, 9, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 6, 0 },
			{0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{5, 0, 2, 6, 0, 0, 4, 0, 7 },
			{0, 0, 0, 0, 0, 4, 1, 0, 0 },
			{0, 0, 0, 0, 9, 8, 0, 2, 3 },
			{0, 0, 0, 0, 0, 3, 0, 8, 0 },
			{0, 0, 5, 0, 1, 0, 0, 0, 0 },
			{0, 0, 7, 0, 0, 0, 0, 0, 0 },
		};
		sudoku.setNumbers(unsolvableSudoku1);
		assertFalse(sudoku.solve());

		//Test unsolvable sudoku 1:
		sudoku.clear();
		int[][] unsolvableSudoku2 = new int[][] {
			{0, 2, 3, 0, 0, 0, 0, 0, 1 },
			{4, 5, 6, 0, 0, 0, 0, 0, 0 },
			{7, 8, 9, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		sudoku.setNumbers(unsolvableSudoku2);
		assertFalse(sudoku.solve());
	}

	@Test
	void testGetNumbers() {
		int[][] testSudoku = new int[][] {
			{0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		sudoku.setNumbers(testSudoku);
		int[][] test = sudoku.getNumbers();
		
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col++) {
				assertEquals(testSudoku[row][col], test[row][col], "Should be " + test[row][col]);
			}
		}
	}

	@Test
	void testSetNumbers() {
		int[][] testSudoku1 = new int[][] {
			{0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		sudoku.setNumbers(testSudoku1);
		assertEquals(8, sudoku.getNumber(0, 2), "Should be 8");
		assertEquals(1, sudoku.getNumber(3, 4), "Should be 1");
		assertEquals(8, sudoku.getNumber(6, 5), "Should be 8");
		
		int[][] testSudoku2 = new int[][] {
			{0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{0, 5, 0, 0, 10, 0, 6, 0, 0 },
			{6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		sudoku.clear();
		assertThrows(IllegalArgumentException.class, () -> sudoku.setNumbers(testSudoku2));
	}
}
