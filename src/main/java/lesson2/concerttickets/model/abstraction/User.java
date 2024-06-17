package lesson2.concerttickets.model.abstraction;

import lesson2.concerttickets.model.abstraction.Entity;

public abstract class User extends Entity {

    public User() {
    }

    public User(Long id) {
        super(id);
    }

    public void printRole() {
        System.out.println("print role in console");
    }

}
