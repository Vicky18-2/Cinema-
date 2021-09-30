package server.dao.mysql.queries;

/**
 * SQL queries to work with user entity
 */
public interface UserQueries {
    String ADD_USER = "INSERT INTO `user`\n" +
            "(`id_user`,`role`,`name`,`email`,`password`,`birthday`,`gender`)\n" +
            "VALUES\n" +
            "(?,?,?,?,?,?,?);";

    String GET_USER_BY_EMAIL = "Select * from user where email = ?";
}
