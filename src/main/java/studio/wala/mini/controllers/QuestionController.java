package studio.wala.mini.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import studio.wala.mini.entities.Question;
import studio.wala.mini.interfaces.QuestionInterface;

import java.util.List;

public class QuestionController implements QuestionInterface {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MINI");
    EntityManager em = emf.createEntityManager();

    public QuestionController() {}

    @Override
    public List<Question> index() throws RuntimeException {
        Query query = em.createQuery("SELECT q FROM Question q");
        return query.getResultList();
    }

    @Override
    public List<Question> index(String name) throws RuntimeException {
        Query query = em.createQuery("SELECT q FROM Question q WHERE q.title LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Question> filter(Integer filter) throws RuntimeException {
        Query query = em.createQuery("SELECT q FROM Question q WHERE q.topic.id = :filter");
        query.setParameter("filter",  filter);
        return query.getResultList();
    }

    @Override
    public List<Question> paginate(Integer page, Integer limit) throws RuntimeException {
        Query query = em.createQuery("SELECT q FROM Question q");
        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Question show(Integer id) throws RuntimeException {
        return em.find(Question.class, id);
    }

    @Override
    public void store(Question question) throws RuntimeException {
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();
    }

    @Override
    public void update(Question question) throws RuntimeException {
        em.getTransaction().begin();
        em.merge(question);
        em.getTransaction().commit();
    }

    @Override
    public void destroy(Integer id) throws RuntimeException {
        em.getTransaction().begin();
        em.remove(em.find(Question.class, id));
        em.getTransaction().commit();
    }

}
