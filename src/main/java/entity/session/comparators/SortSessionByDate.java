package entity.session.comparators;

import entity.session.Session;

import java.util.ArrayList;

public class SortSessionByDate {
    public static ArrayList<Session> sortAsc(ArrayList<Session> sessions) {
        ArrayList<Session> result = new ArrayList<>();
        sessions.stream().sorted(new SessionTimeComparator()).forEach(s -> result.add((Session) s));
        return result;
    }

    public static ArrayList<Session> sortDesc(ArrayList<Session> sessions) {
        ArrayList<Session> result = new ArrayList<>();
        sessions.stream().sorted(new SessionTimeComparator().reversed()).forEach(s -> result.add((Session) s));
        return result;
    }
}
