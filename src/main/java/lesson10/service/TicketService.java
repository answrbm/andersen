package lesson10.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson10.exception.TicketNotFoundException;
import lesson10.model.Ticket;
import lesson10.repository.TicketRepository;
import lesson5.model.BusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        ticketRepository.save(ticket.getUserId(),ticket.getTicketType(),ticket.getCreationDate());
        return ticket;
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with such id not found"));
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    /*
        Here has been used method getTicketById(Long ticketId),
        so if no such ticket, exception will be thrown
     */
    public Ticket updateTicketType(Long ticketId, String ticketType) {
        Ticket ticketToUpdate = getTicketById(ticketId);
        ticketToUpdate.setTicketType(ticketType);
        return ticketRepository.save(ticketToUpdate);
    }

    public Long deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
        return ticketId;
    }

    public List<BusTicket> getBusTickets(Resource resource) {
        List<BusTicket> tickets = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            while(reader.ready()) {
                BusTicket ticket = new ObjectMapper().readValue(reader.readLine(), BusTicket.class);
                tickets.add(ticket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
