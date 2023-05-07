package studio.wala.mini.interfaces;

import studio.wala.mini.entities.Question;

import java.util.List;

public interface QuestionInterface {

    public List<Question> index() throws RuntimeException;
    public List<Question> index(String name) throws RuntimeException;
    public List<Question> filter(Integer filter) throws RuntimeException;
    public List<Question> paginate(Integer page, Integer limit) throws RuntimeException;

    public Question show(Integer id) throws RuntimeException;

    public void store(Question question) throws RuntimeException;
    public void update(Question question) throws RuntimeException;
    public void destroy(Integer id) throws RuntimeException;

}
