package main.view;

import javax.swing.*;

public class Main extends JFrame {

    private JPanel mainPanel;

    public Main(String title) {
        super(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentPane(mainPanel);
        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Login loginFrame = new Login("Login Usuario");
        loginFrame.setVisible(true);

        loginFrame.addListener(() -> {
            Main mainFrame = new Main("Pantalla Principal");
            mainFrame.setVisible(true);
        });
    }

}
