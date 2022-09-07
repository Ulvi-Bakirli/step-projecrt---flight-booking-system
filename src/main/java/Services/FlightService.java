package Services;

import DAO.DAO;
import Entities.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightService implements DAO<Flight> {

    private List<Flight> flightDatabase = new ArrayList<>();

    @Override
    public List<Flight> selectAll() {
        return flightDatabase;
    }

    @Override
    public Optional<Flight> select(int id) {
        return flightDatabase.stream()
                .filter(flight -> flight.getId() == id)
                .findFirst();
    }

    @Override
    public boolean delete(int id) {
        Optional<Flight> select = select(id);
        if (select.isPresent()) {
            Flight flight = select.get();
            flightDatabase.remove(flight);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void insert(Flight flight) {
        flightDatabase.add(flight);
    }
}
