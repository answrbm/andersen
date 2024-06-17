package lesson2.concerttickets.model.abstraction;

import lesson2.concerttickets.model.annotation.NullableWarning;

public abstract class Entity {

    @NullableWarning
    private Long id;

    public Entity() {
    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void print() {
        System.out.println("print content in console");
    }
}
