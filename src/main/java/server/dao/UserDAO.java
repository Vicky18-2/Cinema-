package server.dao;

import entity.user.User;

public interface UserDAO {
    /**
     * @param user object to put into database
     * @return true if success, false if user with this email exists or some error
     */
    boolean addUser(User user);

    /**
     * @param email Email to check in database
     * @param password password to check in database
     * @return true - if authorization success, false - if email or password was't found in database, or error occured
     */
    boolean checkUser(String email, String password);

    /**
     * @param email email to find user
     * @return user object from database
     */
    User getUserByEmail(String email);


}
