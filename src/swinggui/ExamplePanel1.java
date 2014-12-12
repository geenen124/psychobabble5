package swinggui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExamplePanel1 extends JPanel {
	
	public ExamplePanel1() {
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
		
		//content begins here, I just added two meaningless buttons
		JButton Button1 = new JButton("Button");
		Button1.setName("Test");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Button1);
		
		JButton Button2 = new JButton("Another button");
		Button2.setName("Test");
		Button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Button2);
		
		JButton Button3 = new JButton("Even more buttons!");
		Button3.setName("Test");
		Button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Button3);
		
		JButton Button4 = new JButton("Hi there, button here!");
		Button4.setName("Test");
		Button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Button4);
		
		JButton Button5 = new JButton("Button");
		Button5.setName("Test");
		Button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Button5);
		//content ends here
		
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(450,500));
		panel.setMaximumSize(new Dimension(900,500));
		this.add(panel);
	}
	
	public static void main(String[] args) {
		ExamplePanel1 thing = new ExamplePanel1();
		thing.setVisible(true);
	}
}
