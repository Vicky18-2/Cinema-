package entity.session;

import entity.film.Film;
import entity.hall.Hall;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Objects;

/**
 * Jva bean to store cinema session information
 */
public class Session {
    /**
     * id - cinema session id
     * hall - hall for each session
     * film - film for each session
     * dateTime - date for session
     */
    private int id;
    private Hall hall;
    private Film film;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private DateTime dateTime;

    public Session(Hall hall, Film film, DateTime dateTime) {
        this.hall = hall;
        this.film = film;
        this.dateTime = dateTime;
        this.dateTime = dateTime.withZone(DateTimeZone.forID("Europe/Kiev"));
    }

    @Override
    public String toString() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        return "Session{" +
                "id=" + id +
                ", hall=" + hall +
                ", film=" + film +
                ", dateTime=" + dtfOut.print(dateTime) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * @return String of session date in dd.MM - Hh.mm format
     */
    public String getDayAndMonthAndTime() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM - HH:mm");
        return fmt.print(dateTime);
    }

    /**
     * @return Session date hours and minutes
     */
    public String getTime() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
        return fmt.print(dateTime);
    }

    public Session(int id, Hall hall, Film film, DateTime dateTime) {
        this.id = id;
        this.hall = hall;
        this.film = film;
        this.dateTime = dateTime;
        this.dateTime = dateTime;
        this.dateTime = dateTime.withZone(DateTimeZone.forID("Europe/Kiev"));
    }
}
