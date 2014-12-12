package swinggui;

// Import player here

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GenTable extends JPanel {
	private JTable table;
	
	public GenTable() {
		initUI();
	}
	
	/**
	 * Initialize the table within a scroll pane
	 */
	public final void initUI() {
		Object rowDate[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" }};
		
		Object columnNames[] = { "Column 1", "Column2", "Column3" };
		
		table = new JTable(rowDate,columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		//scrollPane.setPreferredSize(new Dimension(300,100));
		this.add(scrollPane); //don't supply borderlayout. I'm using boxlayout for the panels at the moment
		
	}
	
	public void addPlayers(ArrayList<String> players) {
		// Adjust the player model here
	}
	
	public static void main(String[] args) {
		GenTable thing = new GenTable();
		thing.setVisible(true);
	}
}
