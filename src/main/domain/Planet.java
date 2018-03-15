package main.domain;

import java.util.Objects;

/**
 * Represents the planet entity, containing its ID, name and distance from the current point
 */
public class Planet extends BaseEntity {
    private String name;
    private long distance;

    public Planet(int id) {
        super(id);
    }

    public Planet(int id, String name, long distance) {
        super(id);
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return distance == planet.distance &&
                Objects.equals(name, planet.name);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
