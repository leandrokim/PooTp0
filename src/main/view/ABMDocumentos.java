package main.view;

import main.view.ABMFactura.FacturaABM;
import main.view.ABMNotaCredito.NotaCreditoABM;
import main.view.ABMNotaDebito.NotaDebitoABM;

import javax.swing.*;

public class ABMDocumentos extends JFrame {

    private JPanel mainPanel;
    private JButton facturasButton;
    private JButton creditoButton;
    private JButton debitoButton;

    public ABMDocumentos(String title) {
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

        facturasButton.addActionListener(e -> {
            FacturaABM facturaABM = new FacturaABM();
            facturaABM.frame.setVisible(true);
        });

        creditoButton.addActionListener(e -> {
            NotaCreditoABM notaCreditoABM = new NotaCreditoABM();
            notaCreditoABM.frame.setVisible(true);
        });

        debitoButton.addActionListener(e -> {
            NotaDebitoABM notaDebitoABM = new NotaDebitoABM();
            notaDebitoABM.frame.setVisible(true);
        });
    }

}
