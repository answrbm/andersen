package lesson2.concerttickets.exception;

public class TicketBadParamsException extends RuntimeException {

    public TicketBadParamsException(String message) {
        super(message);
    }
}
