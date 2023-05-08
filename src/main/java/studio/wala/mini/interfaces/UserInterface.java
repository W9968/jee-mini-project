package studio.wala.mini.interfaces;

import studio.wala.mini.entities.User;

public interface UserInterface {

    /**
     * functions where we manage authentication and authorization
     *
     * @return
     */
    public boolean register(User u);
    public User login(String username, String password);

    public User show(int id) throws  RuntimeException;
    public void update(User u) throws  RuntimeException;
    public void delete(int id) throws  RuntimeException;

}
