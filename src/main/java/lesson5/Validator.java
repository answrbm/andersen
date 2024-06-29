package lesson5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson5.model.BusTicket;
import lesson5.service.BusTicketService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validator {

    private static BusTicketService busTicketService = new BusTicketService();
    private static Map<String, Integer> violationsCounter = new HashMap<>();
    private static String fileName = "src/main/resources/test.txt";
    private static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        violationsCounter.put("startDate",0);
        violationsCounter.put("price",0);
        violationsCounter.put("ticketType",0);
    }

    public static void main(String[] args) throws JsonProcessingException {
        int totalTicketsCounter = 0;
        int validTicketsCounter = 0;

        int x = 0;
        do {
            String input = getInputFromFile();
            BusTicket ticket = new ObjectMapper().readValue(input, BusTicket.class);
            busTicketService.createBusTicket(ticket);

            if(validate(ticket)) {
                validTicketsCounter++;
            }
            totalTicketsCounter++;
            x++;
        } while (x < 17);

        System.out.println("Total = {"+totalTicketsCounter+"}");
        System.out.println("Valid = {"+validTicketsCounter+"}");
        System.out.println("Most popular violation = {"+getCommonViolation()+"}");

        System.out.println("All tickets: " + busTicketService.getAllBusTickets());
        System.out.println("Ticket found by id: " + busTicketService.getTicketById(5L));
        System.out.println("Delete ticket by id: " + busTicketService.deleteTicketById(5L));
        System.out.println("All tickets: " + busTicketService.getAllBusTickets());
        System.out.println("Get tickets by type and price: " +
                busTicketService.getTicketsByTypeAndPrice("DAY","0"));
    }

    private static boolean checkDate(String date) {
        if(date != null && !date.isBlank()) {
            boolean result = !dateParser(date).isAfter(LocalDate.now());
            if(!result) {
                violationsCounter.put("startDate",violationsCounter.get("startDate")+1);
            }
            return result;
        }
        violationsCounter.put("startDate",violationsCounter.get("startDate")+1);
        return false;
    }

    private static LocalDate dateParser(String date) {
        return LocalDate.parse(date);
    }

    private static boolean checkPrice(String price) {
        if(price != null) {
            int p = Integer.parseInt(price);
            if(p == 0) {
                violationsCounter.put("price",violationsCounter.get("price")+1);
                return false;
            }
            boolean result = p % 2 == 0;
            if(!result) {
                violationsCounter.put("price",violationsCounter.get("price")+1);
            }
            return result;
        }
        return false;
    }

    private static boolean validate(BusTicket busTicket) {
        if(busTicket.getTicketType() == null) {
            violationsCounter.put("ticketType", violationsCounter.get("ticketType")+1);
            return false;
        }
        switch (busTicket.getTicketType()) {
            case "DAY","WEEK","YEAR" -> {
                return checkDate(busTicket.getStartDate()) && checkPrice(busTicket.getPrice());
            }
            case "MONTH" -> {
                if(busTicket.getStartDate() != null) {
                    violationsCounter.put("startDate", violationsCounter.get("startDate")+1);
                }
                return busTicket.getStartDate() == null && checkPrice(busTicket.getPrice());
            }
            default -> {
                violationsCounter.put("ticketType", violationsCounter.get("ticketType")+1);
                return false;
            }
        }
    }

    private static String getCommonViolation() {
        String maxViolationName = "";
        int maxViolationCount = Integer.MIN_VALUE;
        for(Map.Entry<String,Integer> violationEntry : violationsCounter.entrySet()) {
            if(maxViolationName.isEmpty()) {
                maxViolationName = violationEntry.getKey();
                maxViolationCount = violationEntry.getValue();
            }
            if(maxViolationCount < violationEntry.getValue()) {
                maxViolationName = violationEntry.getKey();
                maxViolationCount = violationEntry.getValue();
            }
        }
        return maxViolationName;
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    private static String getInputFromFile() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
