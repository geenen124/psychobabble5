package swinggui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class MainFrame {
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
	public ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public void display() throws UnsupportedLookAndFeelException, ParseException, HeadlessException, IOException{
		//set Look And Feel
		SynthLookAndFeel synth = new SynthLookAndFeel();
		synth.load(MainFrame.class.getResourceAsStream("lookandfeel.xml"), MainFrame.class);
		UIManager.setLookAndFeel(synth);
		
		//create main frame
		JFrame frame = new JFrame("Football Manager 2015");
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		//toolkit
		Toolkit tk = Toolkit.getDefaultToolkit(); //what is this even?
		int boxwidth = (int) tk.getScreenSize().getWidth();
		int boxheight = (int) tk.getScreenSize().getHeight();
		
		//Header panel
		Header header = new Header();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		frame.add(header);
		
		//Center panel begins here
		JPanel Center = new JPanel();
		Center.setLayout(new BoxLayout(Center, BoxLayout.X_AXIS));
		Center.add(new Box.Filler(minSize, prefSize, null));
		
		//adding two panels
		Center.add(new ExamplePanel1());
		Center.add(new ExamplePanel2());	
		
		Center.add(new Box.Filler(minSize, prefSize, null));
		frame.add(Center, BorderLayout.CENTER);
		//Center panel ends here
		
		//temporary, for the help text or something, I'll fix it later
		JPanel Helper = new JPanel();
		Helper.setLayout(new BoxLayout(Helper, BoxLayout.X_AXIS));
		Helper.add(new Box.Filler(minSize, prefSize, prefSize));
		JPanel HelperBox = new JPanel();
		HelperBox.setName("Panel");
		Helper.add(HelperBox);
		Helper.add(new Box.Filler(minSize, prefSize, prefSize));
		frame.add(Helper);
		
		//southern space
		frame.add(Box.createRigidArea(new Dimension(boxwidth,108)));
		
		//some declarations
		frame.setMinimumSize(new Dimension(1280, 720));
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
	}
	
	
	public static void main(String[] args) throws HeadlessException, IOException{
		MainFrame mainframe = new MainFrame();
		try {
			mainframe.display();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}

}
