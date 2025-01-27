package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borrow_Book extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L; // Added serialVersionUID field

    private JTextField txtUserID;
    private JTextField txtISBN;
    private JTextField txtBorrowDate;
    private JTextField returnTxtField;

    public Borrow_Book() {
        setLayout(null);
        setBackground(new Color(255, 215, 0));

        JLabel lblUserID = new JLabel("User ID:");
        lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUserID.setBounds(50, 50, 100, 25);
        add(lblUserID);

        txtUserID = new JTextField();
        txtUserID.setBounds(185, 50, 200, 20);
        add(txtUserID);

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblISBN.setBounds(50, 100, 100, 25);
        add(lblISBN);

        txtISBN = new JTextField();
        txtISBN.setBounds(185, 105, 200, 20);
        add(txtISBN);

        JLabel lblBorrowDate = new JLabel("Borrow Date:");
        lblBorrowDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblBorrowDate.setBounds(50, 150, 100, 25);
        add(lblBorrowDate);

        txtBorrowDate = new JTextField();
        txtBorrowDate.setBounds(185, 155, 200, 20);
        txtBorrowDate.setEditable(false); // Disable editing of the borrow date
        add(txtBorrowDate);

        JLabel lblReturnDate = new JLabel("Return Date:");
        lblReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblReturnDate.setBounds(50, 197, 100, 25);
        add(lblReturnDate);

        returnTxtField = new JTextField();
        returnTxtField.setBounds(185, 202, 200, 20);
        add(returnTxtField);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setForeground(new Color(255, 255, 255));
        btnSubmit.setBackground(new Color(0, 0, 0));
        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSubmit.setBounds(50, 264, 100, 30);
        btnSubmit.addActionListener(this::borrowBook);
        add(btnSubmit);

        // Set the borrow date to the current date
        txtBorrowDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    private void borrowBook(ActionEvent e) {
        String userID = txtUserID.getText().trim();
        String isbn = txtISBN.getText().trim();
        String borrowDate = txtBorrowDate.getText().trim();
        String returnDate = returnTxtField.getText().trim();

        if (userID.isEmpty() || isbn.isEmpty() || borrowDate.isEmpty() || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate if the return date is greater than the borrow date
        try {
            LocalDate borrowLocalDate = LocalDate.parse(borrowDate);
            LocalDate returnLocalDate = LocalDate.parse(returnDate);

            if (returnLocalDate.isBefore(borrowLocalDate)) {
                JOptionPane.showMessageDialog(this, "Return date must be greater than borrow date!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String availableBooksPath = "D:\\eclipse work space\\Library_Management_System\\Available Books.txt";
        String borrowBooksPath = "D:\\eclipse work space\\Library_Management_System\\Borrow Book.txt";

        // Check if the book is available and reduce the quantity by 1
        if (isBookAvailable(isbn, availableBooksPath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(availableBooksPath));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(borrowBooksPath, true));
                 BufferedWriter tempWriter = new BufferedWriter(new FileWriter("D:\\eclipse work space\\Library_Management_System\\temp.txt"))) {

                String line;
                boolean bookBorrowed = false;
                while ((line = reader.readLine()) != null) {
                    String[] bookDetails = line.split(",");
                    if (bookDetails.length >= 3 && bookDetails[1].trim().equals(isbn)) {
                        int availableQuantity = Integer.parseInt(bookDetails[2].trim());
                        if (availableQuantity > 0) {
                            bookBorrowed = true; // Mark the book as borrowed
                            int newQuantity = availableQuantity - 1;
                            tempWriter.write(bookDetails[0] + "," + bookDetails[1] + "," + newQuantity);
                        } else {
                            tempWriter.write(line); // Keep other records as is
                        }
                        tempWriter.newLine();
                    } else {
                        tempWriter.write(line); // Keep other records as is
                        tempWriter.newLine();
                    }
                }

                reader.close();
                tempWriter.close();
                new File(availableBooksPath).delete(); // Delete old available books file
                new File("D:\\eclipse work space\\Library_Management_System\\temp.txt").renameTo(new File(availableBooksPath)); // Rename the temporary file

                if (bookBorrowed) {
                    writer.write("User ID: " + userID);
                    writer.newLine();
                    writer.write("ISBN: " + isbn);
                    writer.newLine();
                    writer.write("Borrow Date: " + borrowDate);
                    writer.newLine();
                    writer.write("Return Date: " + returnDate);
                    writer.newLine();
                    writer.write("--------------------------");
                    writer.newLine();

                    JOptionPane.showMessageDialog(this, "Book borrowed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields after borrowing
                    txtUserID.setText("");
                    txtISBN.setText("");
                    txtBorrowDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    returnTxtField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Book with ISBN " + isbn + " is not available or out of stock!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Book with ISBN " + isbn + " is not available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private boolean isBookAvailable(String isbn, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(","); // Split by comma
                if (bookDetails.length >= 3) { // Ensure there are at least Title, ISBN, and Quantity fields
                    String availableIsbn = bookDetails[1].trim(); // Extract and trim ISBN
                    int availableQuantity = Integer.parseInt(bookDetails[2].trim()); // Extract and parse quantity

                    if (availableIsbn.equals(isbn) && availableQuantity > 0) {
                        return true; // ISBN matches and quantity > 0
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading available books file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // ISBN not found or no available quantity
    }


}
