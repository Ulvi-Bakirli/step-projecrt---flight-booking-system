import Controllers.FileController;
import Entities.Flight;

import java.io.File;
import java.util.List;

public class ClearSpace {

    public static void createNewFile() {
        List<Flight> flightList = FileController.readInitialFile("src/main/java/Files/InitialFlights");
        StringBuilder sb = new StringBuilder();
        for( Flight flight : flightList) {
            sb.append(flight.getFrom()).append(" ")
                    .append(flight.getTo()).append(" ")
                    .append(flight.getDate()).append(" ")
                    .append(flight.getTime()).append(" ")
                    .append(flight.getDuration()).append(" ")
                    .append(flight.getFreeSeats()).append("\n");
        }
        FileController.writeFlightFile(new File("src/main/java/Files/InitialFlights"), String.valueOf(sb));

    }
}
