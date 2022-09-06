import Entity.Flight;
import Entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightController {
    private static final List<Flight> flights = new ArrayList<>();

    static {
        String dateFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String timeFormat = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Flight flight1 = new Flight(1, dateFormat, timeFormat, "Rome", 56, "Pegasus");
        Flight flight2 = new Flight(2, dateFormat, timeFormat, "San-Marino", 56, "Pegasus");
        flights.add(flight1);
        flights.add(flight2);
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public void viewAllFlights() {
        flights.forEach(flight -> System.out.printf("%s\n", flight));
    }

    public Flight getFlightByID(int id) {
        List<Flight> list = flights.stream()
                .filter(flight -> flight.getId() == id)
                .collect(Collectors.toList());

        if (list.size() != 0) {
            return list.get(0);
        } else {
            return Flight.emptyFlight();
        }
    }

    public List<Flight> findFlight(String dest, String date, int peopleNumber) {
        List<Flight> list = flights.stream()
                .filter(flight -> flight.getDestination().equals(dest))
                .filter(flight -> flight.getDate().equals(date))
                .filter(flight -> (flight.getTotatSeat() - flight.getPassengers().size()) > peopleNumber)
                .collect(Collectors.toList());

        if (list.size() == 0) {
            list.add(Flight.emptyFlight());
            return list;
        } else {
            return list;
        }
    }
}
