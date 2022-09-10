package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight {

    public static int nextID = 1;
    private final int id;
    private final String to;
    private final String from;
    private final String date;

    private final String time;
    private final int totalSeat = 150;
    private final String duration;
    private final List<Passenger> passengers = new ArrayList<>();
    private int freeSeats;
    private final String airline;

    public Flight(String to, String from, String date, String time, int freeSeats, String duration, String airline) {
        id = nextID;
        nextID++;
        this.to = to;
        this.from = from;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.airline = airline;
        this.freeSeats = Math.min(freeSeats, totalSeat);
    }

    public Flight(int id, String to, String from, String date, String time, int freeSeats, String duration, String airline) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.date = date;
        this.time = time;
        this.freeSeats = freeSeats;
        this.duration = duration;
        this.airline = airline;
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

    public int getTotalSeat() {
        return totalSeat;
    }

    public String getAirline() {
        return airline;
    }

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
        freeSeats--;
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
        freeSeats++;
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

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getDuration() {
        return duration;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    @Override
    public String toString() {
            return String.format("ID: %d\t||\tfrom: %s\t||\tto: %s\t||\tdate: %s\t||\ttime: %s\t||\tduration: %s\t||\tairline: %s\t\t||\tfree seats: %d\n",
                    id, from,to, date, time, duration, airline, freeSeats);
    }
}
