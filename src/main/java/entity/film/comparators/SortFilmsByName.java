package entity.film.comparators;

import entity.film.Film;

import java.util.ArrayList;

/**
 * Class for sorting list of films
 */
public class SortFilmsByName {
    /**
     * Sort films in ascending order
     *
     * @param list     initial list of films
     * @param language in which language sort
     * @return sorted list of films
     */
    public static ArrayList<Film> sortAsc(ArrayList<Film> list, String language) {
        ArrayList<Film> films = new ArrayList<>();
        if (language == "eng") {
            list.stream().sorted(new FilmNameComparator()).forEach(f -> films.add((Film) f));
        } else {
            list.stream().sorted(new FilmNameUaComparator()).forEach(f -> films.add((Film) f));
        }
        return films;
    }

    /**
     * Sort films in descending order
     *
     * @param list     initial list of films
     * @param language in which language sort
     * @return sorted list of films
     */
    public static ArrayList<Film> sortDesc(ArrayList<Film> list, String language) {
        ArrayList<Film> films = new ArrayList<>();
        if (language == "eng") {
            list.stream().sorted(new FilmNameComparator().reversed()).forEach(f -> films.add((Film) f));
        } else {
            list.stream().sorted(new FilmNameUaComparator().reversed()).forEach(f -> films.add((Film) f));
        }
        return films;
    }
}
