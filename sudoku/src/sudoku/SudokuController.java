package sudoku;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SudokuController {
	
	public SudokuController(Sudoku sudoku) {
		SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku", 300, 300));
	}
	
	private void createWindow(Sudoku sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		frame.pack();
		frame.setVisible(true);
	}

}
