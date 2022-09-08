import Entities.Booking;
import Entities.Flight;
import Entities.Passenger;
import Services.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingController extends BookingService {

    //    FlightControllerkohne flightController = new FlightControllerkohne();
    public void bookFlight(Flight flight, List<Passenger> passengers) {
        passengers.forEach(passenger -> flight.getPassengers().add(passenger));
        Booking booking = new Booking(passengers, flight);
        addBooking(booking);
    }

    public List<Booking> showAllBookings() {
        return super.selectAll();
    }

    public Optional<Booking> getBookingByID(int id) {
        return super.select(id);
    }

    public boolean removeBooking(int id) {
        return super.delete(id);
    }

    public void addBooking(Booking booking) {
        super.insert(booking);
    }
}
