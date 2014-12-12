package swinggui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExamplePanel2 extends JPanel {
	
	public ExamplePanel2() {
		initUI();
	}
	
	/**
	 * Initialize an example panel
	 */
	public final void initUI() {
		//new panel
		JPanel panel = new JPanel();
				
		panel.setOpaque(false);
		panel.setName("Panel");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new Box.Filler(new Dimension(1,5), new Dimension(1,5), new Dimension(1,5)));
				
		//panel title
		JLabel title = new JLabel("Panel title here");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(title);
				
		//content begins here, I just added the table thingy
		panel.add(new GenTable());
		//content ends here
				
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(800,500));
		panel.setMaximumSize(new Dimension(900,500));
		this.add(panel);
	}
	
	public static void main(String[] args) {
		ExamplePanel2 thing = new ExamplePanel2();
		thing.setVisible(true);
	}
}
