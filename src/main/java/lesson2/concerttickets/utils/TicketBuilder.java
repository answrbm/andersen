package lesson2.concerttickets.utils;

import lesson2.concerttickets.model.StadiumSector;
import lesson2.concerttickets.model.Ticket;
import lesson2.concerttickets.service.TicketService;

import java.math.BigDecimal;
import java.util.Map;

public class TicketBuilder {

    public static void createTickets(TicketService ticketService) {
        Ticket ticket1 = new Ticket("1B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket2 = new Ticket("2B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket3 = new Ticket("3B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket4 = new Ticket("4B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket5 = new Ticket("5B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket6 = new Ticket("6B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket7 = new Ticket("7B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket8 = new Ticket("8B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket9 = new Ticket("9B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));
        Ticket ticket10 = new Ticket("10B","1234567890",303,
                System.currentTimeMillis()/1000L,true, StadiumSector.B,
                20.2,new BigDecimal(150));

        ticketService.save(ticket1);
        ticketService.save(ticket2);
        ticketService.save(ticket3);
        ticketService.save(ticket4);
        ticketService.save(ticket5);
        ticketService.save(ticket6);
        ticketService.save(ticket7);
        ticketService.save(ticket8);
        ticketService.save(ticket9);
        ticketService.save(ticket10);
    }
}
