package lesson2.concerttickets.model;

import lesson2.concerttickets.model.abstraction.User;
import lesson2.concerttickets.model.enums.Role;

public class Client extends User {

    private Role role;
    private Ticket ticket;

    public Client() {
        this.role = Role.CLIENT;
    }

    public Client(Long id) {
        super(id);
        this.role = Role.CLIENT;
    }

    @Override
    public void printRole() {
        System.out.println("Role: " + role);
    }

    public void getTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
