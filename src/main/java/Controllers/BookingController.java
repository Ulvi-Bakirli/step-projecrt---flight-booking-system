package Controllers;

import Entities.Booking;
import Entities.Flight;
import Entities.Passenger;
import Services.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingController extends BookingService {


    public void bookFlight(FlightController controller, Flight flight, List<Passenger> passengers) {
        if (isSeatsAvailable(flight, passengers.size())) {
            passengers.forEach(passenger -> flight.addPassenger(passenger));
            passengers.forEach(passenger -> addBooking(new Booking(passenger, flight)));
            controller.update(flight);
            System.out.println("Tickets bought successfully! :)");
//            Booking booking = new Booking(passengers, flight);
//            addBooking(booking);
        } else {
            System.out.println("Free seats not available");
        }
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

    public static boolean isSeatsAvailable(Flight flight, int count){
        return flight.getFreeSeats() >= count;
    }
}
