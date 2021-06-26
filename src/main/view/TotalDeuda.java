package main.view;

import main.java.controllers.ABMController;
import main.java.controllers.ConsultaGeneralController;
import main.java.models.Proveedor.Proveedor;

import javax.swing.*;

public class TotalDeuda extends JFrame {

    private JPanel panel;
    private JTextField cuitProveedor;
    private JButton buscarButton;
    private JLabel nombreProveedor;
    private JLabel deudaProveedor;

    private ConsultaGeneralController controller;
    private ABMController abmController;

    public TotalDeuda(String title) {
        super(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentPane(panel);
        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setup();

    }

    private void setup() {
        controller = ConsultaGeneralController.getInstancia();
        abmController = ABMController.getInstancia();

        buscarButton.addActionListener(e -> {
            Proveedor.DTOProveedor proveedor = abmController.getProveedor(Integer.parseInt(cuitProveedor.getText()));

            if (proveedor == null) {
                JOptionPane.showMessageDialog(null, "Proveedor no existe");
                return;
            }

            nombreProveedor.setText(proveedor.nombreProveedor);

            double deuda = controller.totalDeudaPorProveedor(Integer.parseInt(cuitProveedor.getText()));
            deudaProveedor.setText(Double.toString(deuda));
        });
    }

}
