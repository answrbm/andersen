package lesson10.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson10.exception.TicketNotFoundException;
import lesson10.model.Ticket;
import lesson5.model.BusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketDAOImpl ticketDAO;

    @Autowired
    public TicketService(TicketDAOImpl ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketDAO.save(ticket);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketDAO.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket with such id not found"));
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketDAO.findByUserId(userId);
    }

    /*
        Here has been used method getTicketById(Long ticketId),
        so if no such ticket, exception will be thrown
     */
    public String updateTicketType(Long ticketId, String ticketType) {
        getTicketById(ticketId);
        return ticketDAO.updateTicketType(ticketId,ticketType);
    }

    public Long deleteTicket(Long ticketId) {
        return ticketDAO.deleteById(ticketId);
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
