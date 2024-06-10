package lesson2.concerttickets.model;

import lesson2.concerttickets.model.abstraction.User;
import lesson2.concerttickets.model.enums.Role;

public class Admin extends User {

    private Role role;

    public Admin() {
        this.role = Role.ADMIN;
    }

    public Admin(Long id) {
        super(id);
        this.role = Role.ADMIN;
    }

    @Override
    public void printRole() {
        System.out.println("Role: " + role);
    }

    public void checkTicket(Ticket ticket) {
        System.out.println("Checking ticket " + ticket);
    }
}
