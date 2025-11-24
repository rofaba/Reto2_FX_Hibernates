package org.example.javafx_hibernate.dao;

import org.example.javafx_hibernate.entity.Usuario;

public interface UsuarioDao {

    Usuario buscarPorNombreYPassword(String nombreUsuario, String password) throws Exception;
}
