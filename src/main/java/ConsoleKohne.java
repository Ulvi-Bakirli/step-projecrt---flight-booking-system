import Entities.Flight;

import java.util.List;
import java.util.Scanner;

public class ConsoleKohne {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FlightControllerkohne flightController = new FlightControllerkohne();

        while (true) {
           showMenu();

            int enterValue = scanner.nextInt();
            if (enterValue == 6) {
                break;
            }

            if (enterValue == 1) {
                flightController.viewAllFlights();
            }
            if (enterValue == 2) {
                System.out.println("Enter flight id: ");
                int id = scanner.nextInt();
                System.out.println(flightController.getFlightByID(id));
            } //todo: bunlari - if-lerin icindekleri -  ayri bir metoda yig, oradan cagir. belke switch de istifade ede bilersen
            if (enterValue == 3) {
                System.out.println("Enter destination: ");
                String dest = scanner.next();
                System.out.println("Enter date which you want to fly (date format should be dd/MM/yyyy format): ");
                String date = scanner.next();
                System.out.println("Enter number of people: ");
                int count = scanner.nextInt();
                List<Flight> flights = flightController.findFlight(dest, date, count);
                    System.out.println(flights);
                    if (flights.get(0).isEmpty()) {
                        continue;
                    } else {
                        System.out.println("Enter id for booking, or 0 for back to menu: ");
                        int value = scanner.nextInt();
                        if (value == 0) {
                            continue;
                        } else {
                            //...
                        }
                    }
            }
            //...
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
}
