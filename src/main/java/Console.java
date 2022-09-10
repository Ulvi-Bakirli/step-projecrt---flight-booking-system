import Controllers.BookingController;
import Controllers.FileController;
import Controllers.FlightController;
import Entities.Flight;
import Entities.Passenger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    private static File flightSaveFile = new File("src/main/java/Files/flightSaveFile");


    public void run() {
        while (true) {

            showMenu();
            System.out.print("\nEnter menu id:");

            int enterValue = scanner.nextInt();

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
                myFlights();
            }

            if (enterValue == 6) {
                exit();
                break;
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
//        flightController.getAllFlights()
//                .forEach(flight -> System.out.printf("ID = %d, from Rome to %s\n", flight.getId(), flight.getDestination()));
    }

    public static void showTheFlightInfo() {
        System.out.print("\nEnter flight id: ");
        int id = scanner.nextInt();
        Flight flightByID = flightController.getFlightByID(id);
        if (flightByID.getId() == 0) {
            //todo exception kimi throw ele gorek nece olur
            System.out.print("Not found such a flight\n\n");
        } else {
            System.out.println(flightByID);
        }
    }

    public static void searchAndBookFlight() {
        System.out.print("\nEnter destination (first capital is upper): ");
        String dest = scanner.next();
        System.out.print("\nEnter date which you want to fly (date format should be dd/MM/yyyy format): ");
        String date = scanner.next();
        System.out.print("\nEnter number of people: ");
        int count = scanner.nextInt();
        List<Flight> flights = flightController.findFlight(dest, date, count);

        if (flights.get(0).getId() == 0) {
            System.out.printf("Not found for your search which destination city = %s, date = %s, number of people = %d\n", dest, date, count);
        } else {
            System.out.println(flights);
            System.out.println("Enter id for booking, or 0 for back to menu: ");
            int value = scanner.nextInt();
            if (value == 0) {
                System.out.println();
            } else {
                Flight flight = flightController.getFlightByID(value);
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
                bookingController.bookFlight(flightController, flight, passengerList);
                System.out.println("Booking flight successfully completed :)");
                System.out.println(flight.getPassengers().size());
            }
        }
    }

    public static void cancelBooking() {
        myFlights();
        System.out.println("Which flight you want cancel, please enter id: ");
        int id = scanner.nextInt();

        bookingController.getBookingByID(id);

        if (bookingController.removeBooking(id)) {
            System.out.println("Flight cancelled :(");
        } else {
            System.out.println("Flight couldn`t be canceled");
        }


    }

    public static void myFlights() {
        bookingController.showAllBookings().forEach(System.out::println);
    }

    public static void exit() {
        FileController.writeFile(flightSaveFile, flightController.getAllFlights());
    }
}
