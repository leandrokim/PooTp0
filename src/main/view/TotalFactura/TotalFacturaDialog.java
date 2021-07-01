package main.view.TotalFactura;

import javax.swing.*;
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

        buscarButton.addActionListener(e -> {
            int cuitDelProveedor = Integer.parseInt(cuitProveedor.getText());

            String diaStr = dialabel.getText();

            boolean EsValido = isValid(diaStr);

            if (EsValido == false){
                JOptionPane.showMessageDialog(null, "El dia " + diaStr + " no cumple con el formato dd/MM/yyyy");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dia = LocalDate.parse(diaStr,formatter);

            TotalFactura totalFactura = new TotalFactura(
                    cuitDelProveedor,
                    dia
            );

            totalFactura.frame.setVisible(true);

        });

    }

    public static boolean isValid(final String dia) {

        boolean valid = false;

        try {
            // ResolverStyle.STRICT chequea los dias 30, 31 y tambien los años bisiesto.
            LocalDate.parse(dia,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

            valid = true;

        }
        catch (DateTimeParseException e) {
            e.printStackTrace();
            valid = false;
        }

        return valid;
    }
}


