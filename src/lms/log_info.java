package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class log_info extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private Runnable loginAction; // Callback to notify successful login

    public log_info() {
        setBackground(new Color(255, 255, 255));
        setLayout(null);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(182, 96, 221, 25);
        add(textField);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(63, 93, 87, 24);
        add(lblNewLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(182, 160, 221, 24);
        add(passwordField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(63, 156, 87, 24);
        add(lblPassword);

        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setForeground(new Color(0, 102, 102));
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLogin.setBounds(173, 26, 80, 31);
        add(lblLogin);

        JButton btnNewButton = new JButton("LOG IN");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 128, 128));
        btnNewButton.setBounds(63, 213, 85, 21);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                if (validateCredentials(username, password)) {
                    if (loginAction != null) {
                        loginAction.run(); // Notify the Login_Frame to hide and launch LMS
                    }
                } else {
                    // Show an error message if credentials are invalid
                    JOptionPane.showMessageDialog(log_info.this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnNewButton);
    }

    // Method to validate username and password from file
    private boolean validateCredentials(String username, String password) {
        String storedCredentials = readPasswordFromFile("D:\\eclipse work space\\Library_Management_System\\password.txt");
        if (storedCredentials != null) {
            String[] parts = storedCredentials.split(",");
            if (parts.length == 2) {
                String storedUsername = parts[0];
                String storedPassword = parts[1];
                return storedUsername.equals(username) && storedPassword.equals(password);
            }
        }
        return false;
    }


    // Read the password from the file
    private String readPasswordFromFile(String filePath) {
        String password = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            password = reader.readLine(); // Read the first line (assuming one line password)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public void setLoginAction(Runnable action) {
        this.loginAction = action;
    }
}
