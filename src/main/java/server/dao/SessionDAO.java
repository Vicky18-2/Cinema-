package server.dao;

import entity.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SessionDAO {
    /**
     * @param session object to put into database
     */
    int addSession(Session session);

    /**
     * @param id to find object in database
     * @return Session object from database
     */
    Session getSession(int id);

    /**
     * @param s String with day in format dd.MM
     * @return List of sessions for day
     */
    List<Session> getSessionForDay(String s);

    /**
     * @param sessions to get available seats from
     * @return HashMap filled with cinema sessions and available seats for them
     */
    HashMap<Session, Integer> getSessionSeats(ArrayList<Session> sessions);

    /**
     * @param id id for film
     * @return List of cinema sessions for film id
     */
    ArrayList<Session> getSessionForFilm(int id);

    void makeSessionWithRestrictions(Session session);

}
