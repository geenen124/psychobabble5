package swinggui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Header extends JPanel {
	
	public Header() {
		initUI();
	}
	
	/**
	 * Initialize the header in a jpanel
	 */
	public final void initUI() {
		//new panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//button text
		String[] ButtonText = {
				"Overview ",
				"Statistics",
				"",
				"Positions ",
				"Transfers"
		};
		
		//button names to reference proper style
		String[] ButtonName = {
				"HeaderOuter",
				"HeaderMiddle",
				"HeaderInner",
				"HeaderMiddle",
				"HeaderOuter2"
		};
		
		//create buttons
		for(int i = 0 ; i < 5 ; i++){
			JButton button = new JButton(ButtonText[i]);
			button.setName(ButtonName[i]);
			panel.add(button);
		}
		
		//add panel
		this.add(panel);
	}
	
	public static void main(String[] args) {
		Header thing = new Header();
		thing.setVisible(true);
	}
}
