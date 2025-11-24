package org.example.javafx_hibernate.service;

import org.example.javafx_hibernate.dao.UsuarioDao;
import org.example.javafx_hibernate.dao.UsuarioRepository;
import org.example.javafx_hibernate.entity.Usuario;

public class AuthService {

    private final UsuarioDao usuarioDao;
    private Usuario usuarioActual;

    public AuthService() {
        this.usuarioDao = new UsuarioRepository();
    }

    public boolean login(String nombreUsuario, String password) {
        try {
            Usuario u = usuarioDao.buscarPorNombreYPassword(nombreUsuario, password);
            if (u != null) {
                this.usuarioActual = u;
                return true;
            } else {
                this.usuarioActual = null;
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.usuarioActual = null;
            return false;
        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean esAdmin() {
        return usuarioActual != null &&
                "ADMIN".equalsIgnoreCase(usuarioActual.getRol());
    }

    public void logout() {
        this.usuarioActual = null;
    }
}
