import Controllers.FlightController;
import Entities.Flight;

import java.util.List;
import java.util.Scanner;

public class Console {

    static {
        System.out.println("-------------------------");
        System.out.println("-----Console started-----");
        System.out.println("-------------------------");
    }

    private static FlightController flightController = new FlightController();
    private static Scanner scanner = new Scanner(System.in);

    public void run() {
        while(true) {

            showMenu();
            System.out.print("\nEnter menu id: ");

            int enterValue = scanner.nextInt();

            if (enterValue > 6 || enterValue <= 0){
                System.out.println("Enter valid number, which is between 1-6\n");
            }

            if (enterValue == 6) {
                break;
            }

            if (enterValue == 1) {
                flightController.showAllFlights().forEach(System.out::println);
            }

            if (enterValue == 2) {
                System.out.print("\nEnter flight id: ");
                int id = scanner.nextInt();
                showTheFlightInfo(id);
            }

            //todo: bunlari - if-lerin icindekleri -  ayri bir metoda yig, oradan cagir. belke switch de istifade ede bilersen
            if (enterValue == 3) {
                System.out.print("\nEnter destination: ");
                String dest = scanner.next();
                System.out.print("\nEnter date which you want to fly (date format should be dd/MM/yyyy format): ");
                String date = scanner.next();
                System.out.print("\nEnter number of people: ");
                int count = scanner.nextInt();

                List<Flight> flights = flightController.findFlight(dest, date, count);

                if (flights.get(0).getId() == 0) {
                    System.out.printf("Not found for your search which destination city = %s, date = %s, number of people = %d", dest, date, count);
                } else {
                    System.out.println(flights);
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

    public static void showTheFlightInfo(int id) {
        Flight flightByID = flightController.getFlightByID(id);
        if (flightByID.getId() == 0) {
            System.out.print("Not found such a flight\n\n");
        } else {
            System.out.println(flightByID);
        }
    }
}
