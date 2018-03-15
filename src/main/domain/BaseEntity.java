package main.domain;

/**
 * Represents the base class on which the rest of the entities are built, containing the ID field, common to all of them
 */
public class BaseEntity {
    private int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " { id=" + id + " }";
    }
}
