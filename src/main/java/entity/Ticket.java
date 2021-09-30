package entity;

import entity.hall.Column;
import entity.hall.Seat;
import entity.session.Session;

import java.util.Objects;

/**
 * Java bean to store booked seat
 */
public class Ticket {
    /**
     * id - ticket id
     * session - cinema session for ticket
     * seat - seat in hall for ticket
     * column(row, see column javadoc) - row in hall for ticket
     */
    int id;
    Session session;
    Seat seat;
    Column column;
    TicketStatus status;

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Ticket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", session=" + session +
                ", seat=" + seat +
                ", column=" + column +
                '}';
    }

    public Ticket(Session session, Seat seat, Column column) {
        this.session = session;
        this.seat = seat;
        this.column = column;
        this.status = TicketStatus.UNBLOCKED;
    }

    public String getTicketInfo(String lang) {
        if (lang != null && lang.equals("eng")) {
            return "Movie: " + session.getFilm().getName() + " date " + session.getDayAndMonthAndTime() + " row " + column.getId() + " seat " + seat.getId() + ";";
        }
        return "Фільм: " + session.getFilm().getNameUa() + " Дата " + session.getDayAndMonthAndTime() + " Рядок " + column.getId() + " Місце " + seat.getId() + ";";
    }

    public Ticket(int id, Session session, Seat seat, Column column) {
        this.id = id;
        this.session = session;
        this.seat = seat;
        this.column = column;
        this.status = TicketStatus.UNBLOCKED;
    }
}
