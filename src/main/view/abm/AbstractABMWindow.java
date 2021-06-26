package main.view.abm;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractABMWindow {

    public JFrame frame;
    protected JTable table;

    /**
     * Create the application.
     */

    public AbstractABMWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.setMinimumSize(new Dimension(10, 50));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> borrar());

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificar());

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregar());

        JSeparator separator = new JSeparator();
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addContainerGap(423, Short.MAX_VALUE)
                                .addComponent(btnAgregar)
                                .addGap(18)
                                .addComponent(btnModificar)
                                .addGap(18)
                                .addComponent(btnEliminar)
                                .addContainerGap())
                        .addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnEliminar)
                                        .addComponent(btnModificar)
                                        .addComponent(btnAgregar))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBackground(SystemColor.activeCaption);
        frame.getContentPane().add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel(getTitle());
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel_1.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAutoscrolls(true);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable(getTableModel());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (arg0.getClickCount() == 2)
                    modificar();
            }
        });
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane.setViewportView(table);
    }

    protected abstract String getTitle();

    protected abstract AbstractTableModel getTableModel();

    protected abstract void agregar();

    protected abstract void borrar();

    protected abstract void modificar();

}
