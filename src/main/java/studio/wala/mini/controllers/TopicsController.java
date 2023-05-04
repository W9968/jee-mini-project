package studio.wala.mini.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import studio.wala.mini.entities.Topic;
import studio.wala.mini.interfaces.TopicInterface;

import java.util.List;

public class TopicsController implements TopicInterface {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MINI");
    EntityManager em = emf.createEntityManager();

    public TopicsController() {}

    @Override
    public List<Topic> index() throws RuntimeException {
        Query query = em.createQuery("SELECT t FROM Topic t");
        return query.getResultList();
    }

    @Override
    public List<Topic> index(String name) throws RuntimeException {
        Query query = em.createQuery("SELECT t FROM Topic t WHERE t.topicName LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public Topic show(Integer id) throws RuntimeException {
        return em.find(Topic.class, id);
    }

    @Override
    public void store(Topic topic) throws RuntimeException {
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
    }

    @Override
    public void update(Topic topic) throws RuntimeException {
        em.getTransaction().begin();
        em.merge(topic);
        em.getTransaction().commit();
    }

    @Override
    public void destroy(Integer id) throws RuntimeException {
        em.getTransaction().begin();
        em.remove(em.find(Topic.class, id));
        em.getTransaction().commit();
    }
}
