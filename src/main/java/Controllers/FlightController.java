package Controllers;

import Entities.Flight;
import Services.FlightService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightController extends FlightService {

    {
        String dateFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String timeFormat = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Flight flight1 = new Flight(1, dateFormat, timeFormat, "Rome", 56, "Pegasus");
        Flight flight2 = new Flight(2, dateFormat, timeFormat, "San-Marino", 56, "Pegasus");
        addFlight(flight1);
        addFlight(flight2);
    }

    public List<Flight> showAllFlights() {
        return super.selectAll();
    }

    public Flight getFlightByID(int id) {
        Flight flight;
        Optional<Flight> optionalFlight = super.select(id);
        flight = optionalFlight.orElseGet(Flight::emptyFlight);
        return flight;
    }

    public boolean removeFlight(int id) {
        return super.delete(id);
    }

    public void addFlight(Flight flight) {
        super.insert(flight);
    }

    public List<Flight> findFlight(String dest, String date, int peopleNumber) {
        List<Flight> flights = null;

        if (!dest.equals("") && !date.equals("") && peopleNumber != 0) {
            flights = showAllFlights().stream()
                    .filter(flight1 -> flight1.getDestination().equals(dest))
                    .filter(flight1 -> flight1.getDate().equals(date))
                    .filter(flight1 -> isFreeSeatAvailable(flight1, peopleNumber))
                    .collect(Collectors.toList());
        }

        if (flights == null) {
            flights = new ArrayList<>();
            flights.add(Flight.emptyFlight());
            return flights;
        } else {
            return flights;
        }

    }

    public boolean isFreeSeatAvailable(Flight flight, int count) {
        if (flight.getTotatSeat() - flight.getPassengers().size() > count) {
            return true;
        } else return false;
    }
}
