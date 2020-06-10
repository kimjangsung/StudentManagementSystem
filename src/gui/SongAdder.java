package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SongAdder extends JPanel {

	WindowFrame frame;
	
	public SongAdder(WindowFrame frame) {
		this.frame = frame;
		
		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());
		
		JLabel labelID = new JLabel("Name: ", JLabel.TRAILING);
		
		JTextField fieldID = new JTextField(10);
		
		labelID.setLabelFor(fieldID);
		panel.add(labelID);
		panel.add(fieldID);
		
		JLabel labelName = new JLabel("Artist: ", JLabel.TRAILING);
		
		JTextField fieldName = new JTextField(10);
		
		labelName.setLabelFor(fieldName);
		panel.add(labelName);
		panel.add(fieldName);
		
		JLabel labelEmail = new JLabel("Date: ", JLabel.TRAILING);
		
		JTextField fieldEmail = new JTextField(10);
		
		labelEmail.setLabelFor(fieldEmail);
		panel.add(labelEmail);
		panel.add(fieldEmail);
		
		JLabel labelPhone = new JLabel("Album: ", JLabel.TRAILING);
		JTextField fieldPhone = new JTextField(10);
		
		labelPhone.setLabelFor(fieldPhone);
		panel.add(labelPhone);
		panel.add(fieldPhone);
		panel.add(new JButton("save"));
		panel.add(new JButton("cancel"));

		SpringUtilities.makeCompactGrid(panel, 5, 2, 6, 6, 6, 6);

		this.add(panel);
		this.setVisible(true);

	}
}
