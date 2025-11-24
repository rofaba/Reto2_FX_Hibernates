package org.example.javafx_hibernate;

import org.example.javafx_hibernate.config.HibernateUtil;
import org.example.javafx_hibernate.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TestHibernate {

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Consulta sencilla: listar todos los usuarios
            List<Usuario> usuarios = session
                    .createQuery("from Usuario", Usuario.class)
                    .getResultList();

            for (Usuario u : usuarios) {
                System.out.println(u.getId() + " - " + u.getNombreUsuario() + " - " + u.getRol());
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
            HibernateUtil.shutdown();
        }
    }
}
