package main.domain;

import java.util.Objects;

/**
 * Represents the ship entity, containing its ID, maximum cargo loading, name, speed and type
 */
public class Ship extends BaseEntity {
    private long maxCargoWeight;
    private String name;
    private int speed;
    private String type;

    public Ship(int id, long maxCargoWeight, String name, int speed, String type) {
        super(id);
        this.maxCargoWeight = maxCargoWeight;
        this.name = name;
        this.speed = speed;
        this.type = type;
    }

    public long getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(long maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return maxCargoWeight == ship.maxCargoWeight &&
                speed == ship.speed &&
                Objects.equals(name, ship.name) &&
                Objects.equals(type, ship.type);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "maxCargoWeight=" + maxCargoWeight +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                ", type='" + type + '\'' +
                '}';
    }
}
