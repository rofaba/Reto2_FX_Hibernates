package org.example.javafx_hibernate.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.javafx_hibernate.MainApp;
import org.example.javafx_hibernate.entity.Usuario;
import org.example.javafx_hibernate.service.AuthService;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    private AuthService authService;

    @FXML
    public void initialize() {
        // Obtenemos el AuthService compartido desde MainApp
        this.authService = MainApp.getAuthService();
    }

    @FXML
    private void onLogin(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario == null || usuario.isBlank() ||
                password == null || password.isBlank()) {
            lblMensaje.setText("Usuario y contraseña son obligatorios.");
            return;
        }

        boolean ok = authService.login(usuario, password);

        if (ok) {
            try {
                Usuario u = authService.getUsuarioActual();
                System.out.println("Login correcto: " + u.getNombreUsuario());
                lblMensaje.setText("");
                MainApp.setRoot("main-view");
            } catch (Exception e) {
                e.printStackTrace();
                lblMensaje.setText("Error al cargar la pantalla principal.");
            }
        } else {
            lblMensaje.setText("Credenciales incorrectas.");
        }
    }
}
