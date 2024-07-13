package lesson10.controller;

import lesson10.model.Ticket;
import lesson10.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final String thisIsMyFirstConditionalBean;

    @Autowired
    public TicketController(TicketService ticketService, String thisIsMyFirstConditionalBean) {
        this.ticketService = ticketService;
        this.thisIsMyFirstConditionalBean = thisIsMyFirstConditionalBean;
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable("ticketId") Long ticketId) {
        System.out.println(thisIsMyFirstConditionalBean);
        return ticketService.getTicketById(ticketId);
    }
}
