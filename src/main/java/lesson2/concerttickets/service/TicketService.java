package lesson2.concerttickets.service;

import lesson2.concerttickets.model.enums.StadiumSector;
import lesson2.concerttickets.model.Ticket;
import lesson2.concerttickets.utils.NullableWarningManager;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class TicketService {

    private final Map<Long, Ticket> tickets;

    public TicketService() {
        this.tickets = new HashMap<>();
    }

    public Long save(Ticket ticket) {
        this.tickets.put(ticket.getId(),ticket);
        return ticket.getId();
    }

    public Collection<Ticket> findAll() {
        return tickets.values();
    }

    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        Ticket fullTicket = new Ticket(3454L,"1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket limitedTicket = new Ticket("1234567890",305,System.currentTimeMillis()/1000L);
  
        TicketService ticketService = new TicketService();
        TicketBuilder.createTickets(ticketService);

        NullableWarningManager.checkFields(new Ticket());

        System.out.println(ticketService.findAll());

        emptyTicket.share("+123456789");
        emptyTicket.share("+123456789","test@mail.com");

    }
}
