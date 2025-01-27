package lms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import Panels.About;
import Panels.Available_Book;
import Panels.Borrow_Book;
import Panels.Borrowed_Book;
import Panels.Return_Book;

public class LMS {

    private JFrame frame;
    Available_Book ab = new Available_Book();
    Borrow_Book bb = new Borrow_Book();
    Borrowed_Book brd_b = new Borrowed_Book();
    About abt = new About();
    Return_Book rb = new Return_Book();
    private JLabel lblTime;
    private JLabel lblDate;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LMS window = new LMS();
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LMS() {
        initialize();
        ab.setBackground(new Color(255, 215, 0));
        layeredpanel.add(ab);
        layeredpanel.add(bb);
        layeredpanel.add(brd_b);
        layeredpanel.add(abt);
        layeredpanel.add(rb);
        ab.setVisible(false);
        bb.setVisible(false);
        brd_b.setVisible(false);
        abt.setVisible(false);
        rb.setVisible(false); // Ensure Return_Book is hidden initially

        startDateTimeUpdater(); // Start updating the date and time
    }

    JLayeredPane layeredpanel;

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 215, 0));
        frame.setBounds(100, 100, 1038, 673);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        panel.setBounds(0, 0, 198, 677);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // Time Label
        lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTime.setForeground(new Color(0, 0, 255));
        lblTime.setBounds(18, 581, 88, 20);
        panel.add(lblTime);

        // Date Label
        lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDate.setForeground(new Color(0, 0, 255));
        lblDate.setBounds(18, 611, 180, 20);
        panel.add(lblDate);

        JButton btnNewButton = new JButton("Available Book");
        btnNewButton.addActionListener(e -> switchPanel(ab));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        btnNewButton.setBounds(10, 138, 168, 30);
        panel.add(btnNewButton);

        JButton btnBorrowedBook = new JButton("Borrowed Book");
        btnBorrowedBook.addActionListener(e -> switchPanel(brd_b));
        btnBorrowedBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        btnBorrowedBook.setBounds(10, 204, 168, 30);
        panel.add(btnBorrowedBook);

        JButton btnBorrowBook = new JButton("Borrow Book");
        btnBorrowBook.addActionListener(e -> switchPanel(bb));
        btnBorrowBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        btnBorrowBook.setBounds(10, 261, 168, 30);
        panel.add(btnBorrowBook);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(e -> switchPanel(rb)); // Show Return_Book panel
        btnReturnBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        btnReturnBook.setBounds(10, 319, 168, 30);
        panel.add(btnReturnBook);

        JButton btnAbout = new JButton("About");
        btnAbout.addActionListener(e -> switchPanel(abt));
        btnAbout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        btnAbout.setBounds(10, 384, 168, 30);
        panel.add(btnAbout);

        layeredpanel = new JLayeredPane();
        layeredpanel.setBounds(208, 10, 806, 619);
        frame.getContentPane().add(layeredpanel);
        layeredpanel.setLayout(new CardLayout(0, 0));
    }

    private void switchPanel(JPanel panelToShow) {
        ab.setVisible(false);
        bb.setVisible(false);
        brd_b.setVisible(false);
        abt.setVisible(false);
        rb.setVisible(false); // Hide Return_Book when switching panels
        panelToShow.setVisible(true); // Show the requested panel
    }

    private void startDateTimeUpdater() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                lblTime.setText(now.format(timeFormatter)); // Update time
                lblDate.setText(now.format(dateFormatter)); // Update date
            }
        }, 0, 1000); // Update every second
    }

    public void show() {
        frame.setVisible(true);
    }
}
