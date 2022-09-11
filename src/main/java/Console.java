import Controllers.BookingController;
import Controllers.FileController;
import Controllers.FlightController;
import Entities.Booking;
import Entities.Flight;
import Entities.Passenger;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Console {

    static {
        System.out.println("-------------------------");
        System.out.println("-----Console started-----");
        System.out.println("-------------------------");
    }

    private static FlightController flightController = new FlightController();
    private static BookingController bookingController = new BookingController();
    private static Scanner scanner = new Scanner(System.in);
    private static final File flightSaveFile = new File("src/main/java/Files/flightSaveFile");
    private static final File bookingSaveFile = new File("src/main/java/Files/bookingSaveFile");


    public void run() {
        while (true) {

            showMenu();
            System.out.print("\nEnter menu id:");

            String value = scanner.next();
            try {
                int enterValue = Integer.parseInt(value);
                if (enterValue > 6 || enterValue <= 0) {
                    System.out.println("Enter valid number, which is between 1-6\n");
                }

                if (enterValue == 1) {
                    onlineBoard();
                }

                if (enterValue == 2) {
                    showTheFlightInfo();
                }

                if (enterValue == 3) {
                    searchAndBookFlight();
                }

                if (enterValue == 4) {
                    cancelBooking();
                }

                if (enterValue == 5) {
                    if (myFlights().size() == 0) {
                        System.out.println("You havn`t any bought flight");
                    } else {
                        myFlights().forEach(System.out::println);
                    }
                }

                if (enterValue == 6) {
                    exit();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter only number format");
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. Online board");
        System.out.println("2. Show the flight info");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel the booking");
        System.out.println("5. My flights");
        System.out.println("6. Exit");
    }

    public static void onlineBoard() {

        //show last 12 hours flights
//        flightController.getAllFlights().stream()
//                .filter(flight -> LocalDate.parse(flight.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEqual(LocalDate.now())
//                        && LocalTime.parse(flight.getTime(), DateTimeFormatter.ofPattern("HH:mm")).isAfter(LocalTime.now())
//                        && LocalTime.parse(flight.getTime(), DateTimeFormatter.ofPattern("HH:mm")).isBefore(LocalTime.of(LocalTime.now().getHour() + 12, Integer.parseInt(flight.getTime().substring(3)))))
//                .forEach(flight -> System.out.printf("ID = %d,\t||\tfrom %s to %s,\t||\tdate = %s,\t||\ttime = %s\n",
//                        flight.getId(), flight.getFrom(), flight.getTo(), flight.getDate(), flight.getTime()));
//

        //show last 24 hours flights
        flightController.getAllFlights().stream()
                .filter(flight -> LocalDateTime.of(LocalDate.parse(flight.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalTime.parse(flight.getTime(), DateTimeFormatter.ofPattern("HH:mm")))
                        .isAfter(LocalDateTime.now())
                        &&
                        LocalDateTime.of(LocalDate.parse(flight.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalTime.parse(flight.getTime(), DateTimeFormatter.ofPattern("HH:mm")))
                        .isBefore(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.now().withHour(LocalTime.parse(flight.getTime(), DateTimeFormatter.ofPattern("HH:mm")).getHour()).withMinute(LocalTime.parse(flight.getTime(), DateTimeFormatter.ofPattern("HH:mm")).getMinute()))))
                .forEach(flight -> System.out.printf("ID = %d,\t||\tfrom %s to %s,\t||\tdate = %s,\t||\ttime = %s\n",
                        flight.getId(), flight.getFrom(), flight.getTo(), flight.getDate(), flight.getTime()));
    }

    public static void showTheFlightInfo() {
        System.out.print("\nEnter flight id: ");
        int id = scanner.nextInt();
        Optional<Flight> flightByID = flightController.getFlightByID(id);

        if (flightByID.isEmpty()) {
            System.out.print("Not found such a flight\n\n");
        } else {
            System.out.println(flightByID);
        }

    }

    public static void searchAndBookFlight() {
        System.out.print("\nEnter destination: ");
        String dest = scanner.next().toUpperCase();
        System.out.print("\nEnter date which you want to fly (date format should be dd-MM-yyyy format): ");
        String date = scanner.next();
        System.out.print("\nEnter number of people: ");
        int count = scanner.nextInt();
        List<Flight> flights = flightController.findFlight(dest, date, count);

        if (flights.size() == 0) {
            System.out.printf("Not found for your search which destination city = %s, date = %s, number of people = %d\n", dest, date, count);
        } else {
            System.out.println(flights);
            System.out.println("Enter id for booking, or 0 for back to menu: ");
            int value = scanner.nextInt();
            if (value == 0) {
                System.out.println();
            }
            if (value < 0) {
                System.out.println("Not valid number");
            } else {

                boolean isValidID = false;
                for (Flight flight : flights) {
                    if (value == flight.getId()) {
                        isValidID = true;
                    }
                }

                if (!isValidID) {
                    System.out.println("ID is not inside list");
                } else {
                    Optional<Flight> flight = flightController.getFlightByID(value);

                    if (flight.isPresent()) {
                        List<Passenger> passengerList = new ArrayList<>();
                        for (int i = 0; i < count; i++) {

                            System.out.printf("Enter passenger %d of %d: \n", i + 1, count);
                            System.out.println("Name: ");
                            String name = scanner.next();
                            System.out.print("\nSurname: ");
                            String surname = scanner.next();

                            Passenger passenger = new Passenger(name, surname);
                            passengerList.add(passenger);

                        }
                        bookingController.bookFlight(flightController, flight.get(), passengerList);
                        System.out.println("Booking flight successfully completed :)");
//                System.out.println(flight.getPassengers().size());
                    } else {
                        System.out.println("Flight not found");
                    }
                }
            }
        }
    }

    public static void cancelBooking() {

        if (myFlights().size() == 0) {
            System.out.println("You haven`t any flights");
        } else {
            myFlights().forEach(System.out::println);
            System.out.println("Which flight you want cancel, please enter id: ");
            int id = scanner.nextInt();

            bookingController.getBookingByID(id);

            if (bookingController.removeBooking(id)) {
                System.out.println("Flight cancelled :(");
            } else {
                System.out.println("Flight couldn`t be canceled");
            }
        }
    }

    public static List<Booking> myFlights() {
        return bookingController.showAllBookings();
    }

    public static void exit() {
        FileController.writeFlightFile(flightSaveFile, flightController.getAllFlights());
        FileController.writeBookingFile(bookingSaveFile, bookingController.showAllBookings());
    }
}
