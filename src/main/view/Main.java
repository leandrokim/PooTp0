package main.view;

import main.view.ABMProveedor.ProveedorABM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JPanel mainPanel;
    private JButton proveedoresButton;
    private JButton documentosButton;
    private JButton ordenPagoButton;
    private JButton consultasGeneralesButton;

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

        consultasGeneralesButton.addActionListener(e -> {
            ConsultasGenerales consultasGenerales = new ConsultasGenerales("Consultas Generales");
            consultasGenerales.setVisible(true);
        });

        proveedoresButton.addActionListener(e -> {
            ProveedorABM proveedorABM = new ProveedorABM();
            proveedorABM.frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        Login loginFrame = new Login("Login Usuario");
        loginFrame.setVisible(true);

        loginFrame.addListener(() -> {
            Main mainFrame = new Main("Pantalla Principal");
            mainFrame.setVisible(true);
            loginFrame.dispose();
        });
    }

}
