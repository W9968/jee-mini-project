package studio.wala.mini.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import studio.wala.mini.entities.User;
import studio.wala.mini.interfaces.UserInterface;

public class UserController implements UserInterface {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MINI");
    EntityManager em = emf.createEntityManager();

    public UserController() {}

    @Override
    public boolean register(User u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        return false;
    }

    @Override
    public User login(String username, String password) {
        try {
            return (User) em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User show(int id) throws RuntimeException {
        return em.find(User.class, id);
    }

    @Override
    public void update(User u) throws RuntimeException {
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int id) throws RuntimeException {
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }
}
