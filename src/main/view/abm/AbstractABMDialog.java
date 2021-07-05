package main.view.abm;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractABMDialog<T> extends JDialog {

    private final JPanel contentPanel = new JPanel();
    protected T dto;
    private ModalResult modalResult;

    //Llamarlo en el constructor con super(frame, "titulo", true);
    public AbstractABMDialog(JFrame frame, String title, boolean isModal) {
        super(frame, title, isModal);
        setLocationRelativeTo(frame);
        inicializar();
    }

    protected void inicializar() {
        setSize(500, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        inicializarCampos();

        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(getHorizontalGroup(gl_contentPanel))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(getVerticalGroup(gl_contentPanel))
        );
        contentPanel.setLayout(gl_contentPanel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            asignarDatosEntidad();
            modalResult = ModalResult.OK;
            dispose();
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            modalResult = ModalResult.CANCEL;
            dispose();
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    protected abstract void inicializarCampos();

    protected abstract GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group);

    protected abstract GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group);

    public T getDTO() {
        return dto;
    }

    protected abstract void asignarDatosEntidad();
    //Asignar datos de dto a los campos

    protected abstract void asignarDatosForm();
    //Asginar a los textfields los datos, basicamente en modificar

    public void setDTO(T dto) {
        this.dto = dto;
        asignarDatosForm();
    }

    public ModalResult getModalResult() {
        return modalResult;
    }

}
