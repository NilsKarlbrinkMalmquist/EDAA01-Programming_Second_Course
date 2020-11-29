package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BookReaderController {
	
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		//Pane är en behållarkomponent till vilken de övriga komponenterna (listvy, knappar etc.) ska läggas till.
				
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(counter.getWordList());
		JList<Map.Entry<String, Integer>> listView = new JList<>();
		listView.setModel(listModel);
		
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		//scrollPane.setBorder(new EmptyBorder(5,5,5,5));
		
		JTextField sökfält = new JTextField(20);
		
		JRadioButton button_alphabetic = new JRadioButton("Alphabetic");
		button_alphabetic.addActionListener(event -> listModel.sort((Name1, Name2) -> Name1.getKey().compareTo(Name2.getKey())));
		JRadioButton button_frequency = new JRadioButton("Frequency");
		button_frequency.addActionListener(event -> listModel.sort((Int1, Int2) -> Int2.getValue().compareTo(Int1.getValue())));
		
		ButtonGroup group = new ButtonGroup();
	    group.add(button_alphabetic);
	    group.add(button_frequency);
		
		JButton button_search = new JButton("Search");
		button_search.addActionListener(event -> {
			boolean contains = false;
			for(int i = 0 ; i< listModel.getSize(); i++) {
				if(listModel.getElementAt(i).getKey().equals(sökfält.getText().toLowerCase().trim())) {
					listView.ensureIndexIsVisible(i);
					listView.setSelectedIndex(i);
					contains = true;
				}
			}
			if(contains == false) {
				JOptionPane.showMessageDialog(pane, "Ordet finns inte!");
			}
		});
		
		JPanel panel = new JPanel();
		//panel.setPreferredSize(new Dimension(200, 500));
		panel.add(button_alphabetic);
		panel.add(button_frequency);
		panel.add(sökfält);
		panel.add(button_search);
		
		
		pane.add(scrollPane, BorderLayout.NORTH);
		//pane.setLayout(new GridLayout(2,2));
		pane.add(panel, BorderLayout.SOUTH);
		//pane.add(sökfält, BorderLayout.SOUTH);
		
		JRootPane rootPane = SwingUtilities.getRootPane(button_search); 
		rootPane.setDefaultButton(button_search);

		
		frame.pack();
		frame.setVisible(true);
	}
	
}
