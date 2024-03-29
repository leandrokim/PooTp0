package main.view;

import main.view.ABMOrdenPago.OrdenPagoABM;
import main.view.ABMProveedor.ProveedorABM;
import main.view.ABMUsuario.UsuarioABM;

import javax.swing.*;

public class Main extends JFrame {

    private JPanel mainPanel;
    private JButton proveedoresButton;
    private JButton documentosButton;
    private JButton ordenPagoButton;
    private JButton consultasGeneralesButton;
    private JButton userButton;

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

        documentosButton.addActionListener(e -> {
            ABMDocumentos abmDocumentos = new ABMDocumentos("ABM Documentos");
            abmDocumentos.setVisible(true);
        });

        ordenPagoButton.addActionListener(e -> {
            OrdenPagoABM ordenPagoABM = new OrdenPagoABM();
            ordenPagoABM.frame.setVisible(true);
        });

        userButton.addActionListener(e -> {
            UsuarioABM usuarioABM = new UsuarioABM();
            usuarioABM.frame.setVisible(true);
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
