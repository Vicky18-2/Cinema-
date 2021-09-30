package server.dao.mysql;

import entity.Ticket;
import entity.TicketStatus;
import entity.session.Session;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import server.dao.SessionDAO;
import server.dao.mysql.queries.FilmQueries;
import server.dao.mysql.queries.OrderQueries;
import server.dao.mysql.queries.SessionQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionMySQL implements SessionDAO, Connectable {
    private static final Logger log = Logger.getLogger(SessionMySQL.class);
    private static SessionMySQL dao;

    public static synchronized SessionMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new SessionMySQL();
        }
        return dao;
    }

    @Override
    public int addSession(Session session) {
        Connection con = getConnection();
        PreparedStatement st = null;
        int sessionId;
        try {
            log.debug("Try create session");
            st = con.prepareStatement(SessionQueries.SQL_ADD_NEW_SESSION, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, session.getId());
            st.setInt(2, session.getHall().getId());
            st.setTimestamp(3, new Timestamp(session.getDateTime().getMillis()));
            st.setInt(4, session.getFilm().getId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                sessionId = rs.getInt(1);
                log.debug("Inserted session id --> " + sessionId);
                return sessionId;
            } else {
                log.error("No data inserted");
                throw new SQLException("addSession: No data inserted");
            }
        } catch (SQLException e) {
            log.debug("Error while creating session");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return -1;
    }

    @Override
    public Session getSession(int id) {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try get session from db by id: " + id);
            st = con.prepareStatement(SessionQueries.SQL_GET_SESSION_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return unmapSession(rs);
            }
        } catch (SQLException e) {
            log.debug("Error getting session by id: " + id);
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return null;
    }

    public List<Session> getSessionForDay(String day) {
        Connection con = getConnection();
        CallableStatement st = null;
        ResultSet rs = null;
        ArrayList<Session> result = new ArrayList<>();
        try {
            log.debug("Try get sessions from db by day: " + day);
            st = con.prepareCall(SessionQueries.SQL_GET_SESSION_BY_DAY);
            st.setString(1, day);
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(unmapSession(rs));
            }
            return result;
        } catch (SQLException e) {
            log.debug("Error getting sessions from db by day: " + day);
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return null;
    }

    public ArrayList<Session> getSessionForFilm(int id) {
        ArrayList<Session> result = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try get sessions by film id: " + id);
            st = con.prepareStatement(FilmQueries.SQL_GET_SESSION_FOR_FILM);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(unmapSession(rs));
            }
        } catch (SQLException e) {
            log.debug("Error getting sessions by film id: " + id);
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    @Override
    public void makeSessionWithRestrictions(Session session) {
        ArrayList<Ticket> blockedTickets = new ArrayList<>();
        int sessionId = addSession(session);
        for(int i=1;i<session.getHall().getColumns()+1;i++){
            for(int j=1;j<session.getHall().getSeats()+1;j+=3){
                Ticket ticket = new Ticket(getSession(sessionId),HallMySQL.getInstance().getSeatById(j),HallMySQL.getInstance().getColumn(i));
                ticket.setStatus(TicketStatus.BLOCKED);
                blockedTickets.add(ticket);
            }
        }
        Connection con = getConnection();
        PreparedStatement st = null;
        try{
            st = con.prepareStatement(OrderQueries.SQL_ADD_TICKET);
            for (Ticket t: blockedTickets
                 ) {
                st = OrderMySQL.getInstance().mapTicket(st, t);
                st.setString(5, "BLOCKED");
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    /**
     * @param rs ResultSet to fill the new Session object with data from database
     * @return new Session object
     * @throws SQLException if error occcures during rs.getInt
     */
    public Session unmapSession(ResultSet rs) throws SQLException {
        log.debug("Try to crate Session object from result set");
        return new Session(rs.getInt("id_session")
                , HallMySQL.getInstance().getHall(rs.getInt("id_hall"))
                , FilmMySQL.getInstance().getFilm(rs.getInt("id_film"))
                , new DateTime(rs.getTimestamp("date")));
    }


   /* public List<Session> getAllSession() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Session> result = new ArrayList<>();
        try {
            log.debug("Try to get all session from db");
            st = con.createStatement();
            rs = st.executeQuery(SessionQueries.SQL_GET_ALL_SESSION);
            while (rs.next()) {
                result.add(new Session(rs.getInt("id_session")
                        , HallMySQL.getInstance().getHall(rs.getInt("id_hall"))
                        , FilmMySQL.getInstance().getFilm(rs.getInt("id_film"))
                        , new DateTime(rs.getTimestamp("date"))));
            }
        } catch (SQLException e) {
            log.debug("Error getting all sessions from db");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    */

    public HashMap<Session, Integer> getSessionSeats(ArrayList<Session> sessions) {
        HashMap<Session, Integer> result = new HashMap<>();
        Connection con = getConnection();
        CallableStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try to get seats amount from sessions");
            st = con.prepareCall("select get_available_seats_for_session(?) as seats;");
            for (Session s : sessions
            ) {
                log.debug("Try to get seats amount from session id: " + s.getId());
                st.setInt(1, s.getId());
                rs = st.executeQuery();
                while (rs.next()) {
                    result.put(s, rs.getInt("seats"));
                }
            }
        } catch (SQLException e) {
            log.debug("Error getting seats amount for sessions");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }
}
