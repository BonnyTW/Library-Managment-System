package Panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;

public class About extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public About() {
		setBackground(new Color(255, 215, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("About");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(10, 10, 79, 40);
		add(lblNewLabel);
		
		JTextArea txtrLibraryManagementSystem = new JTextArea();
		txtrLibraryManagementSystem.setBackground(new Color(255, 215, 0));
		txtrLibraryManagementSystem.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 14));
		txtrLibraryManagementSystem.setText("Library Management System\r\n\r\nThis system is designed to manage books and borrowers in a library.\r\nIt has three main tabs: Available Books,  Account, and about.\r\nThe Available Books tab displays a list of available books, and the Account tab displays account information, and about tab displays about the system and the developer.\r\nThe system also has a Return Book feature that allows users to return borrowed books.\r\n\r\nDeveloped by:\r\nBONNY TILAHUN....UGR/30342/15\r\nSAMUEL ZENEBE....UGR//15");
		txtrLibraryManagementSystem.setBounds(20, 60, 776, 549);
		add(txtrLibraryManagementSystem);

	}
}
