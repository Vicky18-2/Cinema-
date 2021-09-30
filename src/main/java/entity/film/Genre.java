package entity.film;

import java.util.Objects;

/**
 * Java bean for genre entity
 */
public class Genre {
    /**
     * id - object id
     * name - genre's name
     */
    private int id;
    private String Name;

    public Genre(String name) {
        this.Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public Genre(int id, String name) {
        this.id = id;
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                Objects.equals(Name, genre.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }
}
