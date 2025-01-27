package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class mod_info extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldOldPassword;
    private JPasswordField passwordFieldNewPassword;
    private JPasswordField passwordFieldConfirmPassword;

    public mod_info() {
        setBackground(new Color(255, 255, 255));
        setLayout(null);

        // Old Password Field
        textFieldOldPassword = new JTextField();
        textFieldOldPassword.setColumns(10);
        textFieldOldPassword.setBounds(225, 96, 184, 22);
        add(textFieldOldPassword);

        JLabel lblOldPassword = new JLabel("Old Password:");
        lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblOldPassword.setBounds(65, 93, 110, 22);
        add(lblOldPassword);

        // New Password Field
        passwordFieldNewPassword = new JPasswordField();
        passwordFieldNewPassword.setBounds(225, 138, 184, 22);
        add(passwordFieldNewPassword);

        JLabel lblNewPassword = new JLabel("New Password:");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewPassword.setBounds(65, 135, 122, 22);
        add(lblNewPassword);

        // Confirm Password Field
        passwordFieldConfirmPassword = new JPasswordField();
        passwordFieldConfirmPassword.setBounds(225, 188, 184, 22);
        add(passwordFieldConfirmPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblConfirmPassword.setBounds(65, 185, 150, 22);
        add(lblConfirmPassword);

        // Modify Title Label
        JLabel lblModify = new JLabel("MODIFY");
        lblModify.setForeground(new Color(0, 102, 102));
        lblModify.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblModify.setBounds(173, 26, 80, 31);
        add(lblModify);

        // Update Password Button
        JButton btnUpdatePassword = new JButton("Update Password");
        btnUpdatePassword.setForeground(new Color(255, 255, 255));
        btnUpdatePassword.setBackground(new Color(0, 128, 128));
        btnUpdatePassword.setBounds(150, 240, 150, 25);
        add(btnUpdatePassword);

        btnUpdatePassword.addActionListener(e -> updatePassword());
    }

    // Method to handle password update
    private void updatePassword() {
        String oldPassword = textFieldOldPassword.getText();
        String newPassword = new String(passwordFieldNewPassword.getPassword());
        String confirmPassword = new String(passwordFieldConfirmPassword.getPassword());

        String filePath = "D:\\eclipse work space\\Library_Management_System\\password.txt";

        // Validate fields
        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "New Password and Confirm Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "New Password and Confirm Password must match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate old password
        if (validateOldPassword(oldPassword, filePath)) {
            // Update the password file
            if (writeNewPassword(newPassword, filePath)) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Old Password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to validate old password
    private boolean validateOldPassword(String oldPassword, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String credentials = reader.readLine();
            if (credentials != null) {
                String[] parts = credentials.split(",");
                if (parts.length == 2) {
                    return parts[1].equals(oldPassword); // Check if old password matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to write the new password to the file
    private boolean writeNewPassword(String newPassword, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("admin," + newPassword); // Update the password
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
