package Entities;

import java.util.List;
import java.util.Objects;

public class Booking {

    private static int nextID = 1;
    private final int id;

    private final Passenger passenger;
    private final Flight flight;

    public Booking(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
        id = nextID;
        nextID++;
    }
   public int getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
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

        return String.format("Booking id = %d,\nPassenger: %s\nFlight: %s\n", id, passenger, flight);
    }
}
