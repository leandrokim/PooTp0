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

    /*
        protected abstract void inicializarCampos(){
            lblNewLabel = new JLabel("DNI");

            txtDNI = new JTextField();
            txtDNI.setColumns(10);

            lblNewLabel_1 = new JLabel("Apellido");

            txtApellido = new JTextField();
            txtApellido.setColumns(10);

            lblNombre = new JLabel("Nombre");

            txtNombre = new JTextField();
            txtNombre.setColumns(10);

            chcSindicalizado = new JCheckBox("Sindicalizado");

            lblNewLabel_2 = new JLabel("Nacimiento");

            txtFNacimiento = new JFormattedTextField();
        }
     */
    protected abstract void inicializarCampos();

    /*
    gl_contentPanel.createSequentialGroup()
        .addContainerGap()
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(lblNewLabel_1)
                .addComponent(lblNewLabel)
                .addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNewLabel_2)
                .addComponent(lblNewLabel_3))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(chcSindicalizado)
                .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(txtSueldo, Alignment.LEADING)
                        .addComponent(txtFNacimiento, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
        .addContainerGap(108, Short.MAX_VALUE)
     */
    protected abstract GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group);

    /*
     gl_contentPanel.createSequentialGroup()
        .addContainerGap()
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblNewLabel)
                .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addGap(4)
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblNewLabel_1)
                .addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblNombre)
                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblNewLabel_2)
                .addComponent(txtFNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblNewLabel_3)
                .addComponent(txtSueldo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(chcSindicalizado)
        .addContainerGap(60, Short.MAX_VALUE)
     */
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
