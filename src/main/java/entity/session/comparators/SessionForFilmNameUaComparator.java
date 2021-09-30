package entity.session.comparators;

import entity.session.Session;

import java.util.Comparator;

/**
 * Filters session for film name in ukrainian
 */
public class SessionForFilmNameUaComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Session s1 = (Session) o1;
        Session s2 = (Session) o2;
        return s1.getFilm().getNameUa().compareTo(s2.getFilm().getNameUa());
    }
}
