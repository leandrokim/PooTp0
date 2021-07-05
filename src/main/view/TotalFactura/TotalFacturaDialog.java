package main.view.TotalFactura;

import main.java.util.DateUtil;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TotalFacturaDialog extends JFrame {

    private JPanel panel;
    private JTextField cuitProveedor;
    private JFormattedTextField dialabel;
    private JButton buscarButton;

    public TotalFacturaDialog(String title) {
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

        try {
            dialabel.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        buscarButton.addActionListener(e -> {
            String cuitDelProveedor = cuitProveedor.getText();

            String diaStr = dialabel.getText();
            diaStr = diaStr.replace(" ", "");

            boolean esValido = isValid(diaStr);

            if (diaStr.length() > 2 && !esValido) {
                JOptionPane.showMessageDialog(null, "El dia " + diaStr + " no cumple con el formato dd/MM/yyyy");
                return;
            }

            TotalFactura totalFactura;

            if (cuitDelProveedor.isEmpty() && diaStr.length() == 2) {
                JOptionPane.showMessageDialog(null, "Ingrese un dia y/o un cuit");
                return;
            }

            if (!cuitDelProveedor.isEmpty() && diaStr.length() == 2) {
                totalFactura = new TotalFactura(
                        Integer.parseInt(cuitDelProveedor),
                        null
                );
            } else if (cuitDelProveedor.isEmpty() && diaStr.length() > 2 && esValido) {
                totalFactura = new TotalFactura(
                        0,
                        DateUtil.toDate(diaStr)
                );
            } else {
                totalFactura = new TotalFactura(
                        Integer.parseInt(cuitDelProveedor),
                        DateUtil.toDate(diaStr)
                );
            }

            totalFactura.frame.setVisible(true);

        });

    }

    public static boolean isValid(final String dia) {
        boolean valid = false;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate.parse(dia, formatter);

            valid = true;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        return valid;
    }
}


