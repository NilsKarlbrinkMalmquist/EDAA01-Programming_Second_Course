package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuController {
	
	public SudokuController() {
		SwingUtilities.invokeLater(() -> createWindow("Sudoku"));
	}
	
	private void createWindow(String title) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		Sudoku sudoku = new Sudoku();					//Creates a sudoku object
		JTextField text[][] = new JTextField[9][9];		//Create 9x9 matrix for text fields 
		
		JPanel buttonPanel = new JPanel();
	    JPanel sudokuPanel = new JPanel();
	    
	    //Sets the layout of the sudoku
	    sudokuPanel.setLayout(new GridLayout(9, 9, 1, 1));
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                text[row][col] = new JTextField();		//Create a textfield for every row, col
                text[row][col].setPreferredSize(new Dimension(40, 40));		//Sets text field size
                //sets background color on every second region:
                if((row / 3) % 2 == 0 && (col / 3) % 2 == 0 || (row / 3) % 2 == 1 && (col / 3) % 2 == 1) {
                	text[row][col].setBackground(Color.pink);
                }                
                text[row][col].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));		//Sets text font and size
                text[row][col].setHorizontalAlignment(JTextField.CENTER);		//Center alignment of text in the text field
                sudokuPanel.add(text[row][col]);		//add the text field at row, col to the sudoku panel
            }
        }
	    
		JButton solveButton = new JButton("Solve");		//Create solve button
		solveButton.addActionListener(event -> {
			int [][] sudokuMatrix = new int [9][9];		//Create 9x9 matrix for integers
			boolean notCorrectNumber = false;					//Boolean that checks if "0" has been assigned to a box
			
			for(int i = 0; i < 9; i++) {
	            for(int j = 0; j < 9; j++) {
	                if(text[i][j].getText().matches("[1-9]") && text[i][j].getText().length() < 2) {
	                	sudokuMatrix[i][j] = Integer.parseInt((text[i][j].getText())); 
	                }
	                else if(text[i][j].getText().isEmpty()) {	//Set empty boxes to 0
	                	sudokuMatrix[i][j] = 0;
	                }
	                else{	//If user has typed in "0" show dialog window and set zerosExist to true
	                	JOptionPane.showInternalMessageDialog(pane, "All numbers must be between 1-9");
	                	notCorrectNumber = true;
	                }
	            }
	        }
			
			if(notCorrectNumber == false) {		//Only try to solve if user has not typed any zeros
				try {						//Try to solve, if number is outside 1-9 catch exception
					sudoku.setNumbers(sudokuMatrix);
				}							
				catch(IllegalArgumentException e) { 
					JOptionPane.showInternalMessageDialog(pane, "All numbers must be between 1-9");
					return;
				}
				if(sudoku.solve() == true) {	//If solvable set the solved sudoku to the text fields:
					for(int i = 0; i < 9; i++) {
						for(int j = 0; j < 9; j++) {
							text[i][j].setText(String.valueOf(sudoku.getNumber(i, j)));
						}
					}
				}
				else {		//if not solvable how message dialog
					JOptionPane.showInternalMessageDialog(pane, "Sudoku is not solvable");
					sudoku.clear();
				}
			}	
		});

		JButton clearButton = new JButton("Clear"); //Creates clear button that clears the board
		clearButton.addActionListener(event ->{
			sudoku.clear();
			for(int i = 0; i < 9; i++) {
	            for(int j = 0; j < 9; j++) {
	                text[i][j].setText("");
	            }
	        }
		});
		
		buttonPanel.add(solveButton, BorderLayout.EAST);
		buttonPanel.add(clearButton, BorderLayout.EAST);
			
		pane.add(buttonPanel, BorderLayout.SOUTH);
		pane.add(sudokuPanel,BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
