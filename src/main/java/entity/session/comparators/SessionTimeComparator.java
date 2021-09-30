package entity.session.comparators;

import entity.session.Session;

import java.util.Comparator;

/**
 * Sorting sessions by time
 */
public class SessionTimeComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Session s1 = (Session) o1;
        Session s2 = (Session) o2;
        return (s1.getDateTime().getHourOfDay() < s2.getDateTime().getHourOfDay())
                ? -1 : ((s1.getDateTime().getHourOfDay() == s2.getDateTime().getHourOfDay()) ? 0 : 1);
    }
}
