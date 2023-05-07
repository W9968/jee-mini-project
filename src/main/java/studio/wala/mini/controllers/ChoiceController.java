package studio.wala.mini.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import studio.wala.mini.entities.Choice;
import studio.wala.mini.interfaces.ChoiceInterface;

import java.util.List;

public class ChoiceController implements ChoiceInterface {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MINI");
    EntityManager em = emf.createEntityManager();

    public ChoiceController() {}

    @Override
    public List<Choice> index(Integer questionId) {
        Query query = em.createQuery("SELECT c FROM Choice c WHERE c.question.id = :questionId");
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }

    @Override
    public Choice show(Integer choiceId) {
        return em.find(Choice.class, choiceId);
    }

    @Override
    public void store(Choice choice) {
        em.getTransaction().begin();
        em.persist(choice);
        em.getTransaction().commit();
    }

    @Override
    public void update(Choice choice) {
        em.getTransaction().begin();
        em.merge(choice);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer choiceId) {
        em.getTransaction().begin();
        em.remove(em.find(Choice.class, choiceId));
        em.getTransaction().commit();
    }
}
