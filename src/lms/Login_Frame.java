package lms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.ImageIcon;

public class Login_Frame {

    private JFrame frame;
    private JLayeredPane layeredPane_1;
    lms.log_info lg_p = new lms.log_info();
    mod_info mod_p = new mod_info();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login_Frame window = new Login_Frame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login_Frame() {
        initialize();
        layeredPane_1.add(lg_p);
        layeredPane_1.add(mod_p);

        lg_p.setVisible(true); // Default visible for the login page
        mod_p.setVisible(false);

        // Adding logic to hide Login_Frame after successful login
        lg_p.setLoginAction(() -> {
            frame.setVisible(false); // Hide the Login_Frame
            EventQueue.invokeLater(() -> {
                try {
                    lms.LMS lmsWindow = new lms.LMS(); // Replace with actual LMS initialization
                    lmsWindow.show(); // Show the LMS frame
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 795, 503);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel_for_2_p = new JPanel();
        panel_for_2_p.setBounds(0, 0, 841, 466);
        frame.getContentPane().add(panel_for_2_p);
        panel_for_2_p.setLayout(null);

        JPanel img_panel = new JPanel();
        img_panel.setBackground(new Color(0, 102, 102));
        img_panel.setForeground(new Color(102, 205, 170));
        img_panel.setBounds(0, 0, 343, 466);
        panel_for_2_p.add(img_panel);
        img_panel.setLayout(null);
        
        JLabel pic_label = new JLabel("");
        pic_label.setIcon(new ImageIcon("C:\\Users\\hp\\Pictures\\Camera Roll\\img.png"));
        pic_label.setBounds(10, 30, 308, 308);
        img_panel.add(pic_label);
        
        JLabel lblNewLabel = new JLabel("LIBRARY");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Cambria Math", Font.BOLD, 15));
        lblNewLabel.setBounds(135, 321, 74, 27);
        img_panel.add(lblNewLabel);
        
        JLabel lblManagment = new JLabel("MANAGMENT");
        lblManagment.setForeground(Color.WHITE);
        lblManagment.setFont(new Font("Cambria Math", Font.BOLD, 15));
        lblManagment.setBounds(120, 348, 97, 27);
        img_panel.add(lblManagment);
        
        JLabel lblSystem = new JLabel("SYSTEM");
        lblSystem.setForeground(Color.WHITE);
        lblSystem.setFont(new Font("Cambria Math", Font.BOLD, 15));
        lblSystem.setBounds(135, 375, 74, 27);
        img_panel.add(lblSystem);

        JPanel info_panel = new JPanel();
        info_panel.setBackground(new Color(255, 255, 255));
        info_panel.setBounds(343, 0, 445, 466);
        panel_for_2_p.add(info_panel);
        info_panel.setLayout(null);

        layeredPane_1 = new JLayeredPane();
        layeredPane_1.setBounds(0, 63, 435, 266);
        info_panel.add(layeredPane_1);
        layeredPane_1.setLayout(new CardLayout(0, 0));

        JLabel lblLoginLabel = new JLabel("Sign in");
        lblLoginLabel.setBounds(10, 355, 98, 31);
        lblLoginLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLoginLabel.setForeground(new Color(0, 128, 128));
        info_panel.add(lblLoginLabel);

        lblLoginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lg_p.setVisible(true);
                mod_p.setVisible(false);
            }
        });

        JLabel lblModifyLabel = new JLabel("Modify");
        lblModifyLabel.setBounds(10, 396, 98, 31);
        lblModifyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblModifyLabel.setForeground(new Color(0, 128, 128));
        info_panel.add(lblModifyLabel);

        lblModifyLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lg_p.setVisible(false);
                mod_p.setVisible(true);
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
