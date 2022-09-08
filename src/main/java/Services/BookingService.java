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
        Optional<Booking> optionalBooking = bookingDatabase.stream()
                .filter(booking -> booking.getId() == id)
                .findFirst();

        if (optionalBooking.isEmpty()) {
            return Optional.of(Booking.emptyBooking());
        } else {
            return optionalBooking;
        }
    }

    @Override
    public boolean delete(int id) {
        Optional<Booking> optionalBooking = select(id);
//        return bookingDatabase.remove(optionalBooking.get());

        return optionalBooking.map(booking -> bookingDatabase.remove(booking)).orElse(false);
    }

    @Override
    public void insert(Booking booking) {
        bookingDatabase.add(booking);
    }
}
