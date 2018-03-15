package main.domain;

import java.util.Objects;

/**
 * Encapsulates the details needed for describing each trip, containing the id of the ship, its name, the number of hours and the total number of trips needed to complete the journey
 */
public class TripDetails extends BaseEntity implements Comparable<TripDetails> {
    private String shipName;
    private int hours;
    private int noOfTrips;

    public TripDetails(int id, String shipName, int hours, int noOfTrips) {
        super(id);
        this.shipName = shipName;
        this.hours = hours;
        this.noOfTrips = noOfTrips;
    }

    public String getShip() {
        return shipName;
    }

    public void setShip(String shipName) {
        this.shipName = shipName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getNoOfTrips() {
        return noOfTrips;
    }

    public void setNoOfTrips(int noOfTrips) {
        this.noOfTrips = noOfTrips;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "shipName=" + shipName +
                ", hours=" + hours +
                ", noOfTrips=" + noOfTrips +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDetails that = (TripDetails) o;
        return hours == that.hours &&
                noOfTrips == that.noOfTrips &&
                Objects.equals(shipName, that.shipName) &&
                getId() == that.getId();
    }

    @Override
    public int compareTo(TripDetails o) {
        return Integer.compare(hours, o.hours);
    }
}
