package Controllers;

import Entities.Booking;
import Entities.Flight;
import Entities.Passenger;
import Enums.Airlines;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FileController {
    public static List<Flight> readInitialFile(String fileName) {
        List<Flight> flights = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //todo fayldan oxundu, liste yazildi
            List<String> flightList = br.lines()
                    .collect(Collectors.toList());

            Airlines[] values = Airlines.values();
            Random random = new Random();

            for (String s : flightList) {
                //todo Airlines enum yaradildi, her defe ozum elle yazacagima avomatik birisini generasiya edir
                int x = random.nextInt(12);

                //todo fayldaki her setir liste yazildi. listden setirler oxundu

                //todo her setir bosluga gore split olundu
                String[] strings = s.split(" ");

                //todo her element goturuldu
                String from = strings[0];
                String to = strings[1];
                String date = strings[2];
                String time = strings[3];
                String duration = strings[4];
                int seats = Integer.parseInt(strings[5]);
//                System.out.println(seats);

                String parse;
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                int daysToMonthEnd = LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth();
                Random random1 = new Random();
                if (localDate.isBefore(LocalDate.now())) {
                    parse = localDate.withYear(LocalDate.now().getYear())
                            .withMonth(LocalDate.now().getMonth().getValue())
                            .withDayOfMonth(LocalDate.now().getDayOfMonth() + random1.nextInt(daysToMonthEnd))
                            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } else {
                    parse = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }


                //todo yeni flight yarandi
                Flight flight = new Flight(from, to, parse, time, seats, duration, values[x].toString());
                flights.add(flight);
//                System.out.println(flight);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    public static List<Flight> readFlightFile(String fileName) {
        List<Flight> flights = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //todo fayldan oxundu, liste yazildi
            List<String> flightList = br.lines()
                    .collect(Collectors.toList());

            Airlines[] values = Airlines.values();
            Random random = new Random();

            for (String s : flightList) {
                //todo Airlines enum yaradildi, her defe ozum elle yazacagima avomatik birisini generasiya edir
                int x = random.nextInt(12);

                //todo fayldaki her setir liste yazildi. listden setirler oxundu

                //todo her setir bosluga gore split olundu
                String[] strings = s.split(" ");

                //todo her element goturuldu
                int id = Integer.parseInt(strings[0]);
                String from = strings[1];
                String to = strings[2];
                String date = strings[3];
                String time = strings[4];
                String duration = strings[5];
                int seats = Integer.parseInt(strings[6]);
                String airline = strings[7];

                //todo yeni flight yarandi
                Flight flight = new Flight(id, from, to, date, time, seats, duration, airline);
                flights.add(flight);
//                System.out.println(flight);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }


    public static void writeFlightFile(File fileName, List<Flight> flights) {

        StringBuilder sb = new StringBuilder();

        //todo line-by-line yazildi
        flights.forEach(flight -> sb.append(flight.getId()).append(" ")
                .append(flight.getFrom()).append(" ")
                .append(flight.getTo()).append(" ")
                .append(flight.getDate()).append(" ")
                .append(flight.getTime()).append(" ")
                .append(flight.getDuration()).append(" ")
                .append(flight.getFreeSeats()).append(" ")
                .append(flight.getAirline()).append("\n"));


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            if (!fileName.exists()) {
                boolean isNewCreated = fileName.createNewFile();
                try {
                    if (isNewCreated) {
                        bw.write(String.valueOf(sb));
                        System.out.printf("File %s created and message was writen\n", fileName.getAbsoluteFile());
                    }
                    if (!isNewCreated) {
                        System.out.println("File could not created");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    bw.write(String.valueOf(sb));
                    System.out.println("File was writen");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeFlightFile(File fileName, String message) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            if (!fileName.exists()) {
                boolean isNewCreated = fileName.createNewFile();
                try {
                    if (isNewCreated) {
                        bw.write(String.valueOf(message));
                        System.out.printf("File %s created and message was writen\n", fileName.getAbsoluteFile());
                    }
                    if (!isNewCreated) {
                        System.out.println("File could not created");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    bw.write(String.valueOf(message));
                    System.out.println("File was writen");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Booking> readBookingFile(File file){
        List<Booking> bookingList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lineList = br.lines().collect(Collectors.toList());

            for (String line : lineList){
                String[] word = line.split(" ");
                int id = Integer.parseInt(word[0]);
                String PassengerName = word[1];
                String PassengerSurname = word[2];
                Passenger passenger = new Passenger(PassengerName, PassengerSurname);

                int fid = Integer.parseInt(word[3]);
                String from = word[4];
                String to = word[5];
                String date = word[6];
                String time = word[7];
                String duration = word[8];
                int seats = Integer.parseInt(word[9]);
                String airline = word[10];

                Flight flight = new Flight(fid, to,from,date, time, seats, duration, airline);
                Booking booking = new Booking(passenger, flight);
                bookingList.add(booking);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }

    public static void writeBookingFile(File file, List<Booking> bookings){
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            for (Booking booking : bookings) {
                sb.append(booking.getId()).append(" ")
                        .append(booking.getPassenger().getName()).append(" ")
                        .append(booking.getPassenger().getSurname()).append(" ")
                        .append(booking.getFlight().getId()).append(" ")
                        .append(booking.getFlight().getFrom()).append(" ")
                        .append(booking.getFlight().getTo()).append(" ")
                        .append(booking.getFlight().getDate()).append(" ")
                        .append(booking.getFlight().getTime()).append(" ")
                        .append(booking.getFlight().getDuration()).append(" ")
                        .append(booking.getFlight().getFreeSeats()).append(" ")
                        .append(booking.getFlight().getAirline()).append("\n");
            }

            //----
            if (!file.exists()) {
                boolean isNewCreated = file.createNewFile();
                try {
                    if (isNewCreated) {
                        writer.write(String.valueOf(sb));
                        System.out.printf("File %s created and message was writen\n", file.getAbsoluteFile());
                    }
                    if (!isNewCreated) {
                        System.out.println("File could not created");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    writer.write(String.valueOf(sb));
                    System.out.println("File was writen");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //----

//            writer.write(sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
