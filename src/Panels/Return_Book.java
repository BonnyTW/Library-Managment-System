package Panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Return_Book extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Return_Book() {
		setBackground(new Color(255, 215, 0));
		setLayout(null);
		
		JLabel bb_label = new JLabel("Return Book");
		bb_label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		bb_label.setBounds(10, 10, 86, 26);
		add(bb_label);
		
		JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserID.setBounds(63, 123, 100, 25);
		add(lblUserID);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIsbn.setBounds(63, 188, 100, 25);
		add(lblIsbn);
		
		JLabel lblReturnedDate = new JLabel("Returned Date:");
		lblReturnedDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReturnedDate.setBounds(63, 251, 115, 25);
		add(lblReturnedDate);

	}

}
