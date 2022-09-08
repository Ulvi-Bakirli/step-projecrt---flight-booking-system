package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Booking {

    private static int nextID = 1;
    private final int id;
    private final List<Passenger> passengers;
    private final Flight flight;

    public Booking(List<Passenger> passenger, Flight flight) {
        this.passengers = passenger;
        this.flight = flight;
        id = nextID;
        nextID++;
    }

    private Booking() {
        flight = Flight.emptyFlight();
        id = 0;
        passengers = new ArrayList<>();
    }

    public static Booking emptyBooking(){
        return new Booking();
    }

    public int getId() {
        return id;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return String.format("Booking id = %d,\nPassengers: %s\nFlight: %s\n", id, passengers, flight);
    }
}
