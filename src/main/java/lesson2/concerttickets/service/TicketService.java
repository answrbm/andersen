package lesson2.concerttickets.service;

import lesson2.concerttickets.exception.TicketNotFoundException;
import lesson2.concerttickets.model.Ticket;
import lesson2.concerttickets.utils.TicketBuilder;

import java.util.*;
import java.util.Random;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class TicketService {

    private final Map<String, Ticket> tickets;

    public TicketService() {
        this.tickets = new HashMap<>();
    }

    public String save(Ticket t) {
        this.tickets.put(t.getId(),t);
        return t.getId();
    }


    public List<Ticket> findByStadiumSector(StadiumSector stadiumSector) {
        return tickets.values()
                .stream()
                .filter(ticket -> ticket.getSector() == stadiumSector)
                .collect(Collectors.toList());
    }

    public Collection<Ticket> findAll() {
        return tickets.values();
    }

    public Ticket findById(String ticketId) {
        if(tickets.containsKey(ticketId)) {
            return tickets.get(ticketId);
        } else {
            throw new TicketNotFoundException("Ticket with such id not found");
        }
    }

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        TicketBuilder.createTickets(ticketService);


        System.out.println(ticketService.findById("2B"));
    }
}
