package main.view;

import main.view.CompulsaDePrecios.CompulsaDePrecios;
import main.view.CompulsaDePrecios.CompulsaDialog;
import main.view.CuentaCorrientesProveedores.CuentaCorrientesProveedores;

import javax.swing.*;

public class ConsultasGenerales extends JFrame {

    private JPanel mainPanel;
    private JButton totalFacturasButton;
    private JButton cuentacorrienteButton;
    private JButton compulsaDePreciosButton;
    private JButton ordenesDePagoButton;
    private JButton totalDeudaButton;
    private JButton retenidosButton;
    private JButton ivaButton;

    public ConsultasGenerales(String title) {
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

        totalFacturasButton.addActionListener(e -> {

        });

        cuentacorrienteButton.addActionListener(e -> {
            CuentaCorrientesProveedores cuentaCorrientesProveedores = new CuentaCorrientesProveedores();
            cuentaCorrientesProveedores.frame.setVisible(true);
        });

        compulsaDePreciosButton.addActionListener(e -> {
            CompulsaDialog compulsaDePrecios = new CompulsaDialog("Compulsa de Precios");
            compulsaDePrecios.setVisible(true);
        });

        ordenesDePagoButton.addActionListener(e -> {

        });

        totalDeudaButton.addActionListener(e -> {
            TotalDeuda totalDeuda = new TotalDeuda("Total Deuda por Proveedor");
            totalDeuda.setVisible(true);
        });

        retenidosButton.addActionListener(e -> {

        });

        ivaButton.addActionListener(e -> {

        });
    }

}