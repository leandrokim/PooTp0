package main.view.CompulsaDePrecios;

import main.java.controllers.ABMController;

import javax.swing.*;

public class CompulsaDialog extends JFrame {

    private JPanel mainPanel;
    private JTextField idProducto;
    private JTextField idRubro;
    private JButton buscar;

    public CompulsaDialog(String title) {
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

        buscar.addActionListener(e -> {
            int productoId = Integer.parseInt(idProducto.getText());
            int rubroId = Integer.parseInt(idRubro.getText());

            if (!ABMController.getInstancia().existeRubro(rubroId)) {
                JOptionPane.showMessageDialog(null, "El Rubro " + rubroId + " no existe");
                return;
            }

            if (!ABMController.getInstancia().existeProducto(rubroId, productoId)) {
                JOptionPane.showMessageDialog(null, "El Producto " + productoId + " no existe");
                return;
            }

            CompulsaDePrecios compulsaDePrecios = new CompulsaDePrecios(
                    productoId,
                    rubroId
            );
            compulsaDePrecios.frame.setVisible(true);
        });

    }

}
