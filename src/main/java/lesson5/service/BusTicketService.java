package lesson5.service;

import lesson5.model.BusTicket;

import java.util.*;
import java.util.stream.Collectors;

public class BusTicketService {

    private final Map<Long, BusTicket> busTickets = new HashMap<>();

    public Long createBusTicket(BusTicket busTicket) {
        busTickets.put(busTicket.getId(),busTicket);
        return busTicket.getId();
    }

    public Collection<BusTicket> getAllBusTickets() {
        return busTickets.values();
    }

    public BusTicket getTicketById(Long busTicketId) {
        return busTickets.get(busTicketId);
    }

    public BusTicket deleteTicketById(Long busTicketId) {
        return busTickets.remove(busTicketId);
    }

    public List<BusTicket> getTicketsByTypeAndPrice(String ticketType, String ticketPrice) {
        return busTickets.values().stream().filter(busTicket -> {
            if(busTicket.getTicketType() != null && busTicket.getPrice() != null) {
                return busTicket.getTicketType().equals(ticketType) && busTicket.getPrice().equals(ticketPrice);
            }
            return false;
        })
        .collect(Collectors.toList());
    }
}
