package Services;

import DAO.DAO;
import Entities.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingService implements DAO<Booking> {

    private List<Booking> bookingDatabase = new ArrayList<>();

    @Override
    public List<Booking> selectAll() {
        return bookingDatabase;
    }

    @Override
    public Optional<Booking> select(int id) {
       return bookingDatabase.stream()
               .filter(booking -> booking.getId() == id)
               .findFirst();
    }

    @Override
    public boolean delete(int id) {
        Optional<Booking> optionalBooking = select(id);

        if (optionalBooking.isPresent()){
            bookingDatabase.remove(optionalBooking.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insert(Booking booking) {
        bookingDatabase.add(booking);
    }
}
