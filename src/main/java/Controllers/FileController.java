package Controllers;

import Entities.Flight;
import Enums.Airlines;

import java.io.*;
import java.util.ArrayList;
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
                String from = strings[1];
                String to = strings[2];
                String date = strings[3];
                String time = strings[4];
                String duration = strings[5];
                int seats = Integer.parseInt(strings[6]);
//                System.out.println(seats);

                //todo yeni flight yarandi
                Flight flight = new Flight(from, to, date, time, seats, duration, values[x].toString());
                flights.add(flight);
//                System.out.println(flight);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    public static List<Flight> readFile(String fileName) {
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
                String from = strings[1];
                String to = strings[2];
                String date = strings[3];
                String time = strings[4];
                String duration = strings[5];
                int seats = Integer.parseInt(strings[6]);

                //todo yeni flight yarandi
                Flight flight = new Flight(from, to, date, time, seats, duration, values[x].toString());
                flights.add(flight);
                System.out.println(flight);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }


    public static void writeFile(File fileName, List<Flight> flights) {

        StringBuilder sb = new StringBuilder();

        //todo line-by-line yazildi
        flights.forEach(flight -> sb.append(flight.getFrom()).append(" ")
                .append(flight.getTo()).append(" ")
                .append(flight.getDate()).append(" ")
                .append(flight.getTime()).append(" ")
                .append(flight.getDuration()).append(" ")
                .append(flight.getFreeSeats()).append("\n"));


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

    public static void writeFile(File fileName, String message) {

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

}
