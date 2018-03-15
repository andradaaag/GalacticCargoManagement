package main.domain;

import java.util.List;
import java.util.Objects;

/**
 * Represents the character entity, containing its ID, name and the types of the ships which can he can drive
 */
public class Character extends BaseEntity {
    private String name;
    private List<String> shipsType;

    public Character(int id) {
        super(id);
    }

    public Character(int id, String name, List<String> shipsType) {
        super(id);
        this.name = name;
        this.shipsType = shipsType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getShipsType() {
        return shipsType;
    }

    public void setShipsType(List<String> shipsType) {
        this.shipsType = shipsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) &&
                Objects.equals(shipsType, character.shipsType);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "name='" + name + '\'' +
                ", shipsType=" + shipsType +
                '}';
    }
}
