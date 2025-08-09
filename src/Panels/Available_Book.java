package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Available_Book extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField nameField, isbnField;
    private DefaultTableModel tableModel;
    private JTable table;
    private JSpinner Quant_spinner;

    public Available_Book() {
        setBackground(new Color(255, 215, 0));
        setLayout(null);

        JLabel ab_label = new JLabel("Available Book");
        ab_label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
        ab_label.setBounds(10, 10, 96, 24);
        add(ab_label);

        JScrollPane avb_scrollPane = new JScrollPane();
        avb_scrollPane.setBounds(20, 50, 458, 357);
        add(avb_scrollPane);

        // Table Model and Table
        String[] columns = {"Book Name", "ISBN", "Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Read from file and populate the table
        loadTableData();

        avb_scrollPane.setViewportView(table); // Add table to the scroll pane

        // Input fields and buttons
        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(20, 441, 58, 13);
        add(lblNewLabel);

        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIsbn.setBounds(20, 476, 58, 13);
        add(lblIsbn);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblQuantity.setBounds(20, 512, 68, 13);
        add(lblQuantity);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.BOLD, 12));
        nameField.setBounds(98, 441, 96, 18);
        add(nameField);
        nameField.setColumns(10);

        isbnField = new JTextField();
        isbnField.setFont(new Font("Tahoma", Font.BOLD, 12));
        isbnField.setBounds(98, 475, 96, 19);
        add(isbnField);
        isbnField.setColumns(10);

        JButton addButton = new JButton("ADD");
        addButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        addButton.setBounds(20, 565, 97, 21);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        add(addButton);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        deleteButton.setBounds(165, 567, 97, 21);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        add(deleteButton);
        
        Quant_spinner = new JSpinner();
        Quant_spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        Quant_spinner.setBounds(98, 511, 30, 20);
        add(Quant_spinner);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hp\\Pictures\\Camera Roll\\img.png"));
        lblNewLabel_1.setBounds(488, 50, 308, 308);
        add(lblNewLabel_1);
    }

    private void loadTableData() {
        tableModel.setRowCount(0); // Clear existing rows
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\eclipse work space\\Library_Management_System\\Available Books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void addBook() {
        String bookName = nameField.getText().trim();
        String isbn = isbnField.getText().trim();
        int quantity = (int) Quant_spinner.getValue();

        if (bookName.isEmpty() || isbn.isEmpty() || quantity == 0) {
            JOptionPane.showMessageDialog(null, "Fill all requirements!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{bookName, isbn, quantity});

        try (FileWriter writer = new FileWriter("D:\\eclipse work space\\Library_Management_System\\Available Books.txt", false)) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String bookNameVal = (String) tableModel.getValueAt(i, 0);
                String isbnVal = (String) tableModel.getValueAt(i, 1);
                String quantityVal = tableModel.getValueAt(i, 2).toString();
                writer.write(bookNameVal + "," + isbnVal + "," + quantityVal + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear input fields
        nameField.setText("");
        isbnField.setText("");
        Quant_spinner.setValue(0);

        // Refresh table
        loadTableData();
    }

    private void deleteBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Remove the selected row from the table
            tableModel.removeRow(selectedRow);

            // Rewrite the file with the updated table data
            try (FileWriter writer = new FileWriter("D:\\eclipse work space\\Library_Management_System\\Available Books.txt", false)) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String bookName = (String) tableModel.getValueAt(i, 0);
                    String isbn = (String) tableModel.getValueAt(i, 1);
                    String quantity = tableModel.getValueAt(i, 2).toString();
                    writer.write(bookName + "," + isbn + "," + quantity + "\n");
                }
                System.out.println("Book deleted and file updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error updating the file.");
            }

            // Refresh table
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a book to delete.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
