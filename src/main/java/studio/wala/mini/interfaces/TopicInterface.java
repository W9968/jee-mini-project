package studio.wala.mini.interfaces;

import studio.wala.mini.entities.Topic;

import java.util.List;

public interface TopicInterface {

    public List<Topic> index() throws RuntimeException;
    public List<Topic> index(String name) throws RuntimeException;
    public Topic show(Integer id) throws RuntimeException;
    public void store(Topic topic) throws RuntimeException;
    public void update(Topic topic) throws RuntimeException;
    public void destroy(Integer id) throws RuntimeException;

}
