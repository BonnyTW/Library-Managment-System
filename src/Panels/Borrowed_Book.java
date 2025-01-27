package Panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Borrowed_Book extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Borrowed_Book() {
		setLayout(null);
		
		JLabel brd_label = new JLabel("Borrowed Book ");
		brd_label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		brd_label.setBounds(10, 10, 102, 22);
		add(brd_label);

	}

}
