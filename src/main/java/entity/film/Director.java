package entity.film;


import java.util.Objects;

/**
 * Java bean file of film director entity
 */
public class Director {
    /**
     * fields to store director information
     */
    private int id;
    private String Name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return id == director.id &&
                Objects.equals(Name, director.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }

    /**
     * Constructor for creating director entity
     *
     * @param id   variable that describe unique object of director class
     * @param name name of director
     */
    public Director(int id, String name) {
        this.id = id;
        Name = name;
    }

    /**
     * Empty constructor
     */
    public Director() {
    }

    @Override
    public String toString() {
        return "Director{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public Director(String name) {
        Name = name;
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
}
