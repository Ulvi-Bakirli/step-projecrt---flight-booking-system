package Services;

import DAO.DAO;
import Entities.Booking;

import java.util.List;
import java.util.Optional;

public class BookingService implements DAO<Booking> {
    @Override
    public List<Booking> selectAll() {
        return null;
    }

    @Override
    public Optional<Booking> select(int id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void insert(Booking booking) {

    }
}
