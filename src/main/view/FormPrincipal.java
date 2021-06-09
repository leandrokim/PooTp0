package main.view;

import javax.swing.*;

public class FormPrincipal extends JFrame {

    private JPanel mainPanel;

    public FormPrincipal(String title){
        super(title);


        setContentPane(mainPanel);
        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        FormPrincipal frame = new FormPrincipal("Titulo del panel");

        frame.setVisible(true);
    }

}
