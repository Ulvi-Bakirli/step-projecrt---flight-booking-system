package Controllers;

import Entities.Flight;
import Services.FlightService;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightController extends FlightService {

    {
        File file = new File("src/main/java/Files/flightSaveFile");
        if (!file.exists()) {
            FileController.readInitialFile("src/main/java/Files/InitialFlights")
                    .forEach(flight -> addFlight(flight));
        } else {
            FileController.readInitialFile("src/main/java/Files/flightSaveFile")
                    .forEach(flight -> addFlight(flight));
        }
    }

    public List<Flight> getAllFlights() {
        return super.selectAll();
    }

    public Flight getFlightByID(int id) {
//        Flight flight;
//        Optional<Flight> optionalFlight = super.select(id);
//        flight = optionalFlight.get();
//        return flight;
        Optional<Flight> optionalFlight = super.select(id);

        if (optionalFlight.isPresent()) {
            return optionalFlight.get();
        } else {
            try {
                throw new Exception("Flight not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean removeFlight(int id) {
        return super.delete(id);
    }

    public void addFlight(Flight flight) {
        super.insert(flight);
    }

    public List<Flight> findFlight(String dest, String date, int peopleNumber) {

        return getAllFlights().stream()
                .filter(flight -> flight.getTo().equals(dest))
                .filter(flight -> flight.getDate().equals(date))
                .filter(flight -> flight.getFreeSeats() >= peopleNumber)
                .collect(Collectors.toList());
    }

    public void update(Flight flight) {
        int id = flight.getId();
        Flight flightByID = getFlightByID(id);
        getAllFlights().remove(flightByID);
        addFlight(flight);
    }
}
