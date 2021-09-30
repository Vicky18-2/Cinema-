package entity.hall;

import java.util.Objects;

public class Hall {
    private int id;
    private int seats;
    private int columns;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id == hall.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", seats=" + seats +
                ", columns=" + columns +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Hall(int id, int seats, int columns) {
        this.id = id;
        this.seats = seats;
        this.columns = columns;
    }
}
