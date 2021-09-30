package entity.session.comparators;


import entity.session.Session;

import java.util.ArrayList;

public class SortSessionByFilmName {
    public static ArrayList<Session> sortAsc(ArrayList<Session> list, String language) {
        ArrayList<Session> sessions = new ArrayList<>();
        if (language == "eng") {
            list.stream().sorted(new SessionForFilmNameComparator()).forEach(f -> sessions.add((Session) f));
        } else {
            list.stream().sorted(new SessionForFilmNameUaComparator()).forEach(f -> sessions.add((Session) f));
        }
        return sessions;
    }

    public static ArrayList<Session> sortDesc(ArrayList<Session> list, String language) {
        ArrayList<Session> sessions = new ArrayList<>();
        if (language == "eng") {
            list.stream().sorted(new SessionForFilmNameComparator().reversed()).forEach(f -> sessions.add((Session) f));
        } else {
            list.stream().sorted(new SessionForFilmNameUaComparator().reversed()).forEach(f -> sessions.add((Session) f));
        }
        return sessions;
    }
}
