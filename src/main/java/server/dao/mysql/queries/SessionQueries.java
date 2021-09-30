package server.dao.mysql.queries;

/**
 * Store strings which contains queries for SQL to work with session entity
 */
public interface SessionQueries {
    String SQL_GET_ALL_SESSION = "Select * from session";
    String SQL_ADD_NEW_SESSION = "Insert into session(id_session, id_hall, date, id_film) values(?,?,?,?)";
    String SQL_GET_SESSION_BY_DAY = "call get_sessions_for_day(?);";
    String SQL_GET_SESSION_BY_ID = "Select * from session where id_session = ?";
}
