package org.example.javafx_hibernate.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.javafx_hibernate.MainApp;
import org.example.javafx_hibernate.entity.Usuario;
import org.example.javafx_hibernate.service.AuthService;

public class MainController {

    @FXML
    private Label lblBienvenida;

    private AuthService authService;

    @FXML
    public void initialize() {
        this.authService = MainApp.getAuthService();
        Usuario u = authService.getUsuarioActual();
        if (u != null) {
            lblBienvenida.setText("Bienvenido, " + u.getNombreUsuario()
                    + " (" + u.getRol() + ")");
        } else {
            lblBienvenida.setText("Bienvenido");
        }
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            authService.logout();
            MainApp.setRoot("login-view");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
