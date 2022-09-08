package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight {
    private final int id;
    private final String date;
    private final String time;
    private final String destination;
    private final int totatSeat;
    private final List<Passenger> passengers = new ArrayList<>();

    private final String airline;

    public Flight(int id, String date, String time, String destination, int totatSeat, String airline) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.destination = destination;
        this.totatSeat = totatSeat;
        this.airline = airline;
    }

    public static Flight emptyFlight() {
        return new Flight(0, "", "", "", 0, "");
    }

    public boolean isEmpty() {
        if (id == 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotatSeat() {
        return totatSeat;
    }

    public String getAirline() {
        return airline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        if (id == 0) {
            return "Couldn`t find such a flight";
        } else {
            return String.format("%3d || %s || %s || Kiev || \t%-20s || %-15s\n", id, date, time, destination, airline);
        }
    }
}
