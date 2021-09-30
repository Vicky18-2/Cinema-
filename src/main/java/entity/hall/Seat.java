package entity.hall;

import java.util.Objects;

public class Seat {
    int id;
    int price;
    Column column;

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", price=" + price +
                ", column=" + column +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Seat(int id, int price, Column column) {
        this.id = id;
        this.price = price;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id &&
                Objects.equals(column, seat.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, column);
    }
}
