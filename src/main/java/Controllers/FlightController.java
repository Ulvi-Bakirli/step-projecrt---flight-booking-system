package Controllers;

import Entities.Flight;
import Services.FlightService;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightController extends FlightService {

    {
        File file = new File("src/main/java/Files/flightSaveFile");
        if (!file.exists()) {
            FileController.readInitialFile("src/main/java/Files/InitialFlights")
                    .forEach(flight -> addFlight(flight));
        } else {
            FileController.readFlightFile("src/main/java/Files/flightSaveFile")
                    .forEach(flight -> addFlight(flight));
        }
    }

    public List<Flight> getAllFlights() {
        return super.selectAll();
    }

    public Optional<Flight> getFlightByID(int id){
        return selectAll().stream()
                .filter(flight -> flight.getId() == id)
                .findFirst();
//
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
        Flight flightByID;
        if (getFlightByID(id).isPresent()){
            flightByID = getFlightByID(id).get();
            getAllFlights().remove(flightByID);
            getAllFlights().add(id - 1, flight);
        } else {
            System.out.println("Flight couldn`t be updated");
        }


//        addFlight(flight);


    }
}
