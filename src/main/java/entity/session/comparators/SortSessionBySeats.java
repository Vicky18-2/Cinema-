package entity.session.comparators;

import entity.session.Session;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Sorts session by available seats in ascending and descending order
 */
public class SortSessionBySeats {
    public static HashMap<Session, Integer> sortAsc(HashMap<Session, Integer> sessions) {
        HashMap<Session, Integer> result;
        result = sessions.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }

    public static HashMap<Session, Integer> sortDesc(HashMap<Session, Integer> sessions) {
        HashMap<Session, Integer> result;
        result = sessions.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }
}
