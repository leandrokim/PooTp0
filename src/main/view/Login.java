package main.view;

import main.java.controllers.UserController;
import main.java.exceptions.UsuarioExistenteException;
import main.java.models.TipoUsuario;

import javax.swing.*;

public class Login extends JFrame {

    public interface LoginListener {
        void onLoginSuccess();
    }

    private final UserController userController;

    private JPanel mainPanel;
    private JTextField nombre;
    private JTextField password;
    private JButton login;
    private JButton registrar;

    public Login(String title) {
        super(title);

        userController = UserController.getInstancia();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentPane(mainPanel);
        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        registrar.addActionListener(e -> {
            try {
                userController.altaUsuario(nombre.getText(), password.getText(), TipoUsuario.USUARIO);
            } catch (UsuarioExistenteException exception) {
                JOptionPane.showMessageDialog(null, "Usuario ya existe");
                return;
            }
            JOptionPane.showMessageDialog(null, "Alta de Usuario exitoso");
        });
    }

    public void addListener(LoginListener loginListener) {
        login.addActionListener(e -> {
            boolean success = userController.login(nombre.getText(), password.getText());
            if (success) {
                loginListener.onLoginSuccess();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o password incorrecto");
            }
        });
    }

}
